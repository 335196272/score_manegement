package com.bo.score.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bo.common.entity.User;
import com.bo.common.enums.LogTypeEnum;
import com.bo.common.service.LogService;
import com.bo.common.util.IpUtils;
import com.bo.common.util.Pager;
import com.bo.common.util.T;
import com.bo.score.entity.Classes;
import com.bo.score.entity.Exam;
import com.bo.score.entity.Score;
import com.bo.score.service.ClassesService;
import com.bo.score.service.ExamService;
import com.bo.score.service.ScoreService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 成绩控制器
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
@Controller
@RequestMapping("/admin/score")
public class ScoreController {

	@Resource
	private ScoreService scoreService;
	
	@Resource
	private LogService logService;
	
	@Resource
	private ClassesService classesService;
	
	@Resource
	private ExamService examService;
	
	/**
	 * 列表
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月19日.<br>
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String list(HttpServletRequest req) {
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
		int numPerPage = T.intValue(req.getParameter("numPerPage"), 100);
		String orderField = T.stringValue(req.getParameter("orderField"), null);
		String orderDirection = T.stringValue(req.getParameter("orderDirection"), null);
		String studentName = T.stringValue(req.getParameter("studentName"), null);
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		Exam exam = examService.findNewestExam(); // 查询最近一次考试
		long examId = T.longValue(req.getParameter("examId"), exam == null ? 0 : exam.getExamId());
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "student_no" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "asc" : orderDirection);
		parameterMap.put("studentName", studentName);
		parameterMap.put("classesId", classesId);
		parameterMap.put("examId", examId);
		Pager<Score> pager = scoreService.pager(parameterMap);
		
		List<Classes> classesList = classesService.listAllClasses();
		List<Exam> examList = examService.listAllExam();
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("total", pager.getTotal());
		req.setAttribute("list", pager.getResultList());
		req.setAttribute("classesList", classesList);
		req.setAttribute("examList", examList);
    	return "admin/score/list"; 
    }
	
	/**
	 * 跳转成绩导入页面
	 * @return<br>
	 * @author DengJinbo, 2017年10月19日.<br>
	 */
	@RequestMapping(value = "/toImport.html", method = RequestMethod.GET)
	public String toImport(HttpServletRequest req) {
		List<Classes> classesList = classesService.listAllClasses();
		List<Exam> examList = examService.listAllExam();
		
		req.setAttribute("classesList", classesList);
		req.setAttribute("examList", examList);
		return "admin/score/import";
	}
	
	/**
	 * 成绩导入
	 * @param req
	 * @return
	 * @author DengJinbo, 2017年10月19日.<br>
	 */
	@ResponseBody
    @RequestMapping(value = "/importFile.html", method = RequestMethod.POST)
	public Map<String, Object> importFile(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		long examId = T.longValue(req.getParameter("examId"), 0);
		User currentUser = (User) req.getSession().getAttribute("currentUser");
		
		Classes classes = classesService.find(classesId);
		if (classes == null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "班级不存在!");
			return resultMap;
		}
		Exam exam = examService.find(examId);
		if (exam == null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "考试不存在!");
			return resultMap;
		}
		
		int count = 0; // 导入成功计数器
		int fail = 0; // 导入失败计数器
		StringBuffer notExistsBuf = new StringBuffer(); // 必填项为空
		StringBuffer repeatedBuf = new StringBuffer(); // 成绩已经存在（班级 + 座号 + 考试）
		
		MultipartHttpServletRequest re = (MultipartHttpServletRequest) req;
        MultipartFile scoreFile = re.getFile("scoreFile");
        CommonsMultipartFile cf = (CommonsMultipartFile) scoreFile;
        
		FileItem fi = cf.getFileItem();
		if (fi != null && !fi.isFormField()) {
			Workbook wb = null;
			try {
				InputStream is = fi.getInputStream();
				wb = Workbook.getWorkbook(is);
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("status", 300);
				resultMap.put("msg", "读取Excel表格出错，请检查Excel表格， 或者稍后重试!");
				return resultMap;
			}
			// 读取第一个工作本
			Sheet sheet = wb.getSheet(0);
			if (sheet != null) {
				int rowNum = sheet.getRows();
				Cell[] cells = null;
				Cell cell = null;
				
				Score score = null; // 成绩实体
				long studentNo = 0L; // 座号
				String studentName = ""; // 学生姓名
				BigDecimal scores = null; // 成绩
				
				// 从第三行开始拿数据
				for (int i = 2; i < rowNum; i++) {
					cells = sheet.getRow(i);
					if (cells != null && cells.length > 0) {
						score = new Score();
						// A.座号
						if (0 < cells.length) {
							cell = cells[0];
						} else {
							cell = null;
						}
						if (cell != null) {
							studentNo = T.longValue(cell.getContents(), 0);
							if (studentNo > 0) {
								if (scoreService.findByCondition(classesId, studentNo, examId) == null) {
									score.setStudentNo(studentNo);
								} else {
									fail++;
									repeatedBuf.append((i + 1) + "、");
									continue;
								}
							} else {
								fail++;
								notExistsBuf.append((i + 1) + "、");
								continue;
							}
						} else {
							fail++;
							notExistsBuf.append((i + 1) + "、");
							continue;
						}
						// B.学生姓名
						if (1 < cells.length) {
							cell = cells[1];
						} else {
							cell = null;
						}
						if (cell != null) {
							studentName = cell.getContents();
							if (!T.isBlank(studentName)) {
								score.setStudentName(studentName);
							} else {
								fail++;
								notExistsBuf.append((i + 1) + "、");
								continue;
							}
						} else {
							fail++;
							notExistsBuf.append((i + 1) + "、");
							continue;
						}
						// C.成绩
						if (2 < cells.length) {
							cell = cells[2];
						} else {
							cell = null;
						}
						if (cell != null) {
							try {
								scores = new BigDecimal(cell.getContents());
							} catch (Exception e) {
								scores = new BigDecimal(0);
							}
							score.setScore(scores);
						} else {
							fail++;
							notExistsBuf.append((i + 1) + "、");
							continue;
						}
						score.setClassesId(classesId);
						score.setExamId(examId);
						score.setCreateBy(currentUser.getUserId());
						score.setCreateDate(T.getNow());
						score.setUpdateBy(currentUser.getUserId());
						score.setUpdateDate(T.getNow());
						scoreService.create(score);
						count++;
					} else {
						fail++;
						notExistsBuf.append((i + 1) + "、");
						continue;
					}
				}
				List<Score> scoreList = scoreService.listByScoreDesc(classesId, examId);
				Score dataScore = null;
				for (int i = 0; i < scoreList.size(); i++) {
					dataScore = scoreList.get(i);
					dataScore.setRank(i + 1);
					scoreService.update(dataScore);
				}
			}
		} else {
			resultMap.put("status", 300);
			resultMap.put("msg", "文件为空，请重新上传!");
			return resultMap;
		}
		
		String msg = "成功导入" + count + "条数据。" + ((0 > fail) ? "" : ("导入失败:" + fail + "条数据!"));
		if (fail > 0) {
			msg += "原因：";
			if (!T.isBlank(notExistsBuf.toString())) {
				msg += "必填项为空，行号为：" + notExistsBuf.toString() + "；";
			}
			if (!T.isBlank(repeatedBuf.toString())) {
				msg += "成绩已存在，行号为：" + repeatedBuf.toString() + "；";
			}
		}
		logService.log(currentUser.getUserName(), LogTypeEnum.CREATE.getDesc(), "用户：" 
				+ currentUser.getUserName() + "，导入成绩。" + msg, IpUtils.getIp());
		
		resultMap.put("status", 200);
		resultMap.put("msg", msg);
		return resultMap;
	}
	
	/**
	 * 根据条件导出成绩
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception<br>
	 * @author DengJinbo, 2017年12月3日.<br>
	 */
	@RequestMapping(value = "/exportFile.html", method = RequestMethod.GET)
	public String exportFile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long classesId = T.longValue(req.getParameter("classesId"), 0);
		long examId = T.longValue(req.getParameter("examId"), 0);
        
		Classes classes = null;
		if (classesId > 0) {
			classes = classesService.find(classesId);
		}
		Exam exam = examService.find(examId);
		if (exam == null) {
			return null;
		}
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("classesId", classesId);
		parameterMap.put("examId", examId);
		parameterMap.put("orderField", "student_no");
		parameterMap.put("orderDirection", "asc");
		List<Score> list = scoreService.list(parameterMap);
		Score score = null;
		
        // 开始导出数据
        String fileName = URLEncoder.encode((classes != null ? classes.getName() : "") + exam.getName() + "登记表", "UTF-8"); // 导出的文件名
        HSSFWorkbook workBook = null; // 表格
        HSSFRow row = null; // 每一行
        OutputStream os = null; // 输出流，创建表格过程的内容都写在输出流里
        try {
	        if (null != list && list.size() > 0) {
	        	os = resp.getOutputStream(); // 获取输出流
	        	resp.reset(); // 清空输出流
	        	resp.setContentType("application/vnd.ms-excel"); // 设置输出类型为excel
	        	// 解决下载文件名包含中文时出现乱码，要先设置contentType
	        	resp.setHeader("Content-disposition", "attachment; filename=" + new String((fileName + ".xls").getBytes(), "UTF-8"));
	        	workBook = new HSSFWorkbook(); // 创建excel表格
	        	HSSFSheet sheet = null; // 每张表
	        	int rowNum = 0; // 行数
	        	// 创建一张表
	        	sheet = workBook.createSheet(exam.getName() + "（" + T.format(exam.getExamTime(), "MM月dd日")  + "）"); 
	        	// 合并单元格
	            CellRangeAddress region = new CellRangeAddress(0, // first row
	                    0, // last row
	                    0, // first column
	                    3  // last column
	            );
	            sheet.addMergedRegion(region);
	        	if (null != sheet) {
	        		// 标题样式
	        		HSSFCellStyle titleCellStyle = workBook.createCellStyle(); // 表格样式
	        		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 内容居中
		        	HSSFFont titleFont = workBook.createFont();
		        	titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        	titleFont.setFontName("宋体");
		        	titleFont.setFontHeightInPoints((short)16);
		        	titleCellStyle.setFont(titleFont);
		        	// 创建表第一行
    				row = sheet.createRow(rowNum);
    				HSSFCell titleCell = row.createCell(0);
    				titleCell.setCellValue((classes != null ? classes.getName() : "") + "语文" + exam.getName() + "登记表");
    				titleCell.setCellStyle(titleCellStyle);
    				
    				// 表头样式
	        		HSSFCellStyle headCellStyle = workBook.createCellStyle();
	        		headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 内容居中
		        	HSSFFont headFont = workBook.createFont();
		        	headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        	headFont.setFontName("宋体");
		        	headFont.setFontHeightInPoints((short)12);
		        	headCellStyle.setFont(headFont);
		        	headCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框    
		        	headCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框    
		        	headCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框    
		        	headCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框 
		        	
		        	// 创建表第二行
    				rowNum = 1;
    				row = sheet.createRow(rowNum);
    				HSSFCell cell = row.createCell(0);
    				cell.setCellValue("座号");
    				cell.setCellStyle(headCellStyle);
    				cell = row.createCell(1);
    				cell.setCellValue("姓名");
    				cell.setCellStyle(headCellStyle);
    				cell = row.createCell(2);
    				cell.setCellValue("总分");
    				cell.setCellStyle(headCellStyle);
    				cell = row.createCell(3);
    				cell.setCellValue("名次");
    				cell.setCellStyle(headCellStyle);
    				rowNum = 2; // 下一行开始为第三行
    			}
        		HSSFCellStyle cellStyle = workBook.createCellStyle(); // 表格样式
        		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 内容居中
	        	HSSFFont font = workBook.createFont(); //字体样式
	        	font.setFontName("宋体");
	        	font.setFontHeightInPoints((short)12);
	        	cellStyle.setFont(font);
	        	cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框    
	        	cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框    
	        	cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框    
	        	cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框   
	        	for (int i = 0; i < list.size(); i++) {
	        		score = list.get(i);
	        		if (null == score) {
	        			continue;
	        		}
	        		// 写入成绩数据
	        		row = sheet.createRow(rowNum);
	        		HSSFCell cell = row.createCell(0);
	        		cell.setCellValue(score.getStudentNo());
    				cell.setCellStyle(cellStyle);
    				cell = row.createCell(1);
    				cell.setCellValue(score.getStudentName());
    				cell.setCellStyle(cellStyle);
    				cell = row.createCell(2);
    				cell.setCellValue((score.getScore() + "").replace(".0", ""));
    				cell.setCellStyle(cellStyle);
    				cell = row.createCell(3);
    				cell.setCellValue(score.getRank());
    				cell.setCellStyle(cellStyle);
	        		rowNum ++;
	        	}
	        	// 分数段人数统计
	        	int fullMarks = exam.getFullMarks();
	        	int second = (int) (fullMarks * 0.9);
	        	int three = (int) (fullMarks * 0.8);
	        	int four = (int) (fullMarks * 0.7);
	        	int five = (int) (fullMarks * 0.6);
	        	
	        	int	firstLevel = scoreService.countByScore(classesId, examId, fullMarks, fullMarks + 1); // 满分
	        	int	secondLevel = scoreService.countByScore(classesId, examId, second, fullMarks); // 90 - 100
	        	int	thirdLevel = scoreService.countByScore(classesId, examId, three, second); // 80 - 90
	        	int	fourthLevel = scoreService.countByScore(classesId, examId, four, three); // 70 - 80
	        	int	fifthLevel = scoreService.countByScore(classesId, examId, five, four); // 60 - 70
	        	int	sixthLevel = scoreService.countByScore(classesId, examId, 0, five); // 不及格
	        	
	        	rowNum ++;
	        	row = sheet.createRow(rowNum);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(fullMarks + ":" + firstLevel + "人");
				cell.setCellStyle(cellStyle);
				rowNum ++;
				
				row = sheet.createRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue(second + "分以上:" + secondLevel + "人");
				cell.setCellStyle(cellStyle);
				rowNum ++;
				
				row = sheet.createRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue(three + "分以上:" + thirdLevel + "人");
				cell.setCellStyle(cellStyle);
				rowNum ++;
				
				row = sheet.createRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue(four + "分以上:" + fourthLevel + "人");
				cell.setCellStyle(cellStyle);
				rowNum ++;
				
				row = sheet.createRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue(five + "分以上:" + fifthLevel + "人");
				cell.setCellStyle(cellStyle);
				rowNum ++;
				
				row = sheet.createRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue("不及格:" + sixthLevel + "人");
				cell.setCellStyle(cellStyle);
	        }
        	if (null != workBook) {
    			workBook.write(os);
    			os.flush();
    			os.close();
        	}
        	if (os != null) {
        		os.close();
        	}
        	resp.setHeader("Content-disposition", "attachment; filename=" + new String((fileName + ".xls").getBytes(), "UTF-8"));
        } catch (IOException e) {
        	
        } finally {
        	try {
        		if (null != os) {
        			os.close();
        		}
        	} catch (IOException ex){
        		
        	}
        }
        return null;
	}
}