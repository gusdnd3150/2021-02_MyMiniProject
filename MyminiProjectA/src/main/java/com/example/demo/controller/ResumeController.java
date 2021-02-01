package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.service.ResumeService;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;
import com.google.gson.JsonArray;

@Controller
public class ResumeController {
	
	@Autowired
	private ResumeService service;

	@RequestMapping("/resiResume.do")
	public String ResiResum(HttpServletRequest request,Model model) {
		UserVo user = (UserVo) request.getSession().getAttribute("USER");
		UserVo selectUser = service.selectUserDetail(user);
		model.addAttribute("userDetail", selectUser);
		
		return "/resume/resiResume";
	}
	
	@ResponseBody
	@PostMapping("/insertResume.do")
	public String insertResume(@RequestBody String info
	/* ,MultipartHttpServletRequest upfile,HttpServletRequest request */) {
		String result ="success";
		
		System.out.println(info);
	    result=service.insertResume(info);
		
		return result;
	}

}
