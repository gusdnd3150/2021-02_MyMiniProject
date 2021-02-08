package com.example.demo.hireVo;

import org.springframework.stereotype.Component;

import com.example.demo.vo.UserVo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class HireVo extends UserVo {
	
	private int hire_id;
	private int id;
	
	private String hire_title; // 제목
	private String hire_open;  //공개여부
	private String hire_condition; //경력조건
	private String hire_educate; // 학력
	private String hire_state;  //고용형태
	private String hire_salary; // 급여
	private String hire_location; // 회사위치 
	private String hire_workTime; // 주5일 등등
	private String hire_rank;  // 직급   사원, 등
	
	private String hire_address1;  // 주소
	private String hire_address2;  // 상세주소
	private String hire_zip;  // 우편
	

}
