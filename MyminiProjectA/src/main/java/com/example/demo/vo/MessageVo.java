package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class MessageVo {
	
	private int message_id;
	private int id;       //fk  보내는사람
	private int message_receiver;  // 받는사람
	
	private String message_content; //내용
	private String message_date; //날짜
	private String message_check; //확인여부
	

}
