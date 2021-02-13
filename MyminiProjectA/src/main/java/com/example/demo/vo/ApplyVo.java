package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class ApplyVo {
	
	private int apply_id; //pk
	private int id;     //fk
	private int hire_id;//fk
	private int resume_id;//fk
	
	private String apply_result; //지원결과
	private String apply_date;  //지원일

}
