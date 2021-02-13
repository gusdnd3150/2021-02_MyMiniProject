package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class MessageVo {
	
	private int message_id; //pk
	private int id;       //fk  보내는사람
	private int message_receiver;  // 받는사람
	private String message_content; //내용
	private String message_date; //날짜
	private String message_check; //확인여부
	private String message_title; //확인여부
	
	
	/* 유저 정보 */
	private String user_id;         //아이디
	private String user_name;    	//이름
	private String user_email;		//이메일
	private String user_password;	//비밀번호
	private String joinDate;		//가입일
	private String autho;		  //권한   user,admin,company
}
