package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.vo.UserVo;

public class ResumeController {

	
	
	@RequestMapping("/ResiResume.do")
	public String ResiResum(HttpServletRequest request,Model model) {
		
		UserVo user = (UserVo) request.getSession().getAttribute("USER");
		
		return "resiResume";
	}
}
