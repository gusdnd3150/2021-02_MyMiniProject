package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MyPageService;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Controller
public class MyPageController {
	
	
	@Autowired
	private MyPageService service;
	
	
	//페이지이동
	@RequestMapping("/myPage.do")
	public String mypage(Model model,HttpServletRequest request) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		
		List<ResumeVo> list= service.selectResume(user.getId());
		
		model.addAttribute("resumeList", list);
		model.addAttribute("userDetail", user);
				
		return "/mypage/myPage";
	}

}
