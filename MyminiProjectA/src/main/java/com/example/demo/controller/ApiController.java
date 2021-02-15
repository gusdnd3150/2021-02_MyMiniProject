package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	
	
	//네이버 콜백 url 처리할 메서드
	@RequestMapping("/naverLogin.do")
	public String naverUrl(Map<String,Object> info) {
		System.out.println(info);
		return "";
	}

}
