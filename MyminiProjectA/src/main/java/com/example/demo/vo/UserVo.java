package com.example.demo.vo;


import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class UserVo  {
	
	private int id;
	private String user_id;         //아이디
	private String user_name;    	//이름
	private String user_email;		//이메일
	private String user_password;	//비밀번호
	private String joinDate;		//가입일
	private String autho;		  //권한   user,admin,company
	
	//유저 디테일정보
	private String user_phone; 		//전화번호
	private String user_age;			//나이
	private String user_gender;		//성별
	private String user_zip;			//우편번호
	private String user_address1;	//기본주소
	private String user_address2;	//상세주소
	private String user_profile;	//유저 프로필 이미지
	
	
	//회사 디테일정보
	private String company_logo;	//이미지로고
	private String company_intro;	//간단한 소개
	private String company_owner;	//대표
	private String company_name;	//회사이름
	private String company_homepage;	//홈페이지
	private String company_address1;	//상세주소1
	private String company_address2;	//상세주소2
	private String company_zip;	//우편번호
	


}

