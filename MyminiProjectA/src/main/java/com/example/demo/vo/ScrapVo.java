package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class ScrapVo {
	
	private int scrap_id; // pk
	private int id;       //유저 fk
	private int hire_id;  // 채용공고 fk
}
