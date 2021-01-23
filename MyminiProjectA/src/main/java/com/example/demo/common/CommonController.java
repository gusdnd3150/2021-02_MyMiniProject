package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	

}
