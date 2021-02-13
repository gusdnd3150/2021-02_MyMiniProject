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
	
	//회사 디테일정보
	private String company_logo;	//이미지로고
	private String company_intro;	//간단한 소개
	private String company_owner;	//대표
	private String company_name;	//회사이름
	private String company_homepage;	//홈페이지
	private String company_address1;	//상세주소1
	private String company_address2;	//상세주소2
	private String company_zip;	//우편번호
	private String company_startDay;	//설립일
	private String company_sales;	//연매출
	private String company_people;	//사원수
	private String company_money;	//자본금
	
	
	

}
