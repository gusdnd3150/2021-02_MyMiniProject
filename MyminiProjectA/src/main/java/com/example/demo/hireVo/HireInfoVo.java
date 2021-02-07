package com.example.demo.hireVo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class HireInfoVo {
	
	private int hire_info_id;
	private int id;
	
	private String  hire_info_sector; //모집분야
	private int hire_info_count;  // 모집인원
	private String  hire_info_content; // 직무내용
	private String  hire_info_detail; // 모집상세
	private String  hire_info_want; //우대사항
	
}

