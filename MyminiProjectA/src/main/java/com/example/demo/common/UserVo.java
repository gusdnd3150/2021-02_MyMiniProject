package com.example.demo.common;


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
	
	//디테일정보
	private String user_phone; 		//전화번호
	private String user_age;			//나이
	private String user_gender;		//성별
	private String user_zip;			//우편번호
	private String user_address1;	//기본주소
	private String user_address2;	//상세주소


}

