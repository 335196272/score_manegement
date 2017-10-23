package com.bo.score.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
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
		int numPerPage = T.intValue(req.getParameter("numPerPage"), 15);
		String orderField = T.stringValue(req.getParameter("orderField"), null);
		String orderDirection = T.stringValue(req.getParameter("orderDirection"), null);
		String studentName = T.stringValue(req.getParameter("studentName"), null);
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		long examId = T.longValue(req.getParameter("examId"), 0);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "chinese" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "desc" : orderDirection);
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
		StringBuffer repeatedBuf = new StringBuffer(); // 成绩已经存在（班级 + 学生 + 考试）
		
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
				String studentName = ""; // 问题标题
				BigDecimal chinese = null; // 语文成绩
				
				// 从第二行开始拿数据
				for (int i = 1; i < rowNum; i++) {
					cells = sheet.getRow(i);
					if (cells != null && cells.length > 0) {
						score = new Score();
						// A.学生姓名
						if (0 < cells.length) {
							cell = cells[0];
						} else {
							cell = null;
						}
						if (cell != null) {
							studentName = cell.getContents();
							if (!T.isBlank(studentName)) {
								if (scoreService.findByCondition(classesId, studentName, examId) == null) {
									score.setStudentName(studentName);
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
						// B.语文成绩
						if (1 < cells.length) {
							cell = cells[1];
						} else {
							cell = null;
						}
						if (cell != null) {
							chinese = new BigDecimal(cell.getContents());
							score.setChinese(chinese);
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
}