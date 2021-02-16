package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.resumeVo.ResumeMultiVo;
import com.example.demo.service.ResumeService;
import com.example.demo.vo.PortfolioFileVo;
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
		List<PortfolioFileVo> fileList=service.selectUserFile(user);
		
		model.addAttribute("fileList", fileList);
		model.addAttribute("userDetail", selectUser);
		
		return "resume/resiResumeTest";
	}
	
	/* ,MultipartHttpServletRequest upfile,HttpServletRequest request */
	@ResponseBody
	@PostMapping("/insertResume.do")
	public String insertResume(@RequestBody String info,HttpServletRequest request ) {
		String result ="success";
		
	    result=service.insertResume(info,request);
	    
		return result;
	}
	
	// 폼데이터 테스트용
	@ResponseBody
	@PostMapping("/resumeInsertTest.do")
	public String resumeInsertTest(MultipartHttpServletRequest request,ResumeMultiVo resumeMultiVo ) {
		String result ="success";
		System.out.println(resumeMultiVo.toString());
		 MultipartFile f = request.getFile("profileImage");
		 System.out.println(f.getOriginalFilename());
		
		return result;
	}
	
	
	@PostMapping("/uploadProfileImage.do")
	@ResponseBody
	public String uploadProfile(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		String result=null;
		
		result=service.updateImage(upfile,request);
		return result;
	}
	
	
}
