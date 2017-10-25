package com.bo.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bo.common.util.T;
import com.bo.score.entity.Classes;
import com.bo.score.entity.Exam;
import com.bo.score.service.ClassesService;
import com.bo.score.service.ExamService;
import com.bo.score.service.ScoreService;
import com.bo.score.vo.ScoreRankChartVo;
import com.bo.score.vo.ScoreRankVo;

/**
 * 公共视图控制器
 * @author DengJinbo.
 * @Time 2017年9月8日
 */
@Controller
public class CommonController {
    
	@Resource
	private ExamService examService;
	
	@Resource
	private ScoreService scoreService;
	
	@Resource
	private ClassesService classesService;
	
	/**
	 * 后台首页
	 * @return<br>
	 * @author DengJinbo, 2017年9月8日.<br>
	 */
    @RequestMapping("/admin/index.html")
    public String index() {
        return "admin/index";
    }

    /**
     * 后台登录页
     * @return<br>
     * @author DengJinbo, 2017年9月8日.<br>
     */
    @RequestMapping("/admin/login.html")
    public String login() {
        return "admin/login";
    }
    
    /**
     * 后台注册页
     * @return<br>
     * @author DengJinbo, 2017年9月14日.<br>
     */
    @RequestMapping("/admin/register.html")
    public String register() {
        return "admin/register";
    }
    
    /**
     * 后台欢迎页面
     * @return<br>
     * @author DengJinbo, 2017年9月18日.<br>
     */
    @RequestMapping("/admin/welcome.html")
    public String welcome(HttpServletRequest req) {
    	Exam exam = examService.findNewestExam();
		long examId = T.longValue(req.getParameter("examId"), exam == null ? 0 : exam.getExamId());
		List<Classes> classesList = classesService.listAllClasses();
		
		List<ScoreRankVo> scoreRankVoList = scoreService.listScoreRankVo(examId, classesList);
		ScoreRankChartVo scoreRankChartVo = scoreService.listScoreRankChartVo(examId, classesList);
		
    	req.setAttribute("exam", exam);
    	req.setAttribute("scoreRankVoList", scoreRankVoList);
    	req.setAttribute("scoreRankChartVo", scoreRankChartVo);
    	return "admin/welcome";
    }
}