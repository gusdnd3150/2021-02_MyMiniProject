package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class PortfolioFileVo {
	
	private int file_id; //pk
	private int id; // 유저 fk
	private String file_original_name;   //파일진짜이름
	private String file_saved_name;     // 저장된 이름

}

