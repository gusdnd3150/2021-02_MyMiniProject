package com.example.demo.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {
	
	
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
	
	@RequestMapping("/test")
	public String test() {
		return "/common/slideCard";
	}
	  
	//재능 등록
	@GetMapping("/addConfidence.do")
	public String confidence() {
		
		return "addCondfidence";
	}
	
	
	

}
