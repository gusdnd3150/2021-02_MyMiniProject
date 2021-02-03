package com.example.demo.resumeVo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class LanguageVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private int resume_la_id;  //pk
	private String resume_la_type;  // 언어종류
	private String resume_la_level;  // 언어 숙련도
	private String useLanguageForm;
}
