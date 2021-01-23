package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	
	@Autowired
	CommonService service;
	
	@RequestMapping("/main.do")
	public String main() {
		
		
		return "main";
	}

}
