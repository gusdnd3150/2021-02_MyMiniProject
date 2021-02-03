package com.example.demo.resumeVo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class SelfintroVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private String resume_self_content;  // 자기소개서
}
