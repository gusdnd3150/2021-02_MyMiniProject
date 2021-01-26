package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.UserVo;
import com.example.demo.service.CommonService;

import lombok.extern.java.Log;

@Log
@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	CommonService service;
	
	//메인화면
	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}
	
	//로그인폼
	@RequestMapping("/loginForm.do")
	public String loginForm() {
		return "loginForm";
	}
	
	//유저 로그인
	@PostMapping("/userLogin.do")
	@ResponseBody
	public String login(UserVo user,HttpServletRequest request) {
				String result=null;
				result= service.loginUserCheck(user, request);
				return result;
	}
	
	
	@PostMapping("/login.do")
	public void loginProcessing(String error) {
		logger.info("login-processing!");
		logger.info("err :" + error );
	}
	
	@RequestMapping("/test")
	public String test() {
		return "/common/slideCard";
	}
	  
	//재능 등록
	@GetMapping("/addConfidence.do")
	public String confidence() {
		
		return "addCondfidence";
	}
	
	// 가입페이지 이동
	@RequestMapping("/joinForm.do")
	public String joinform() {
		return "/joinForm";
	}
	
	//회원가입
	@ResponseBody
	@RequestMapping("/userJoin.do")
	public String joinUser(UserVo user) {
		String result=null;
		result = service.userJoin(user);
		return result;
	}

}
