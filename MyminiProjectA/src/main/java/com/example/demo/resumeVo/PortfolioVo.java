package com.example.demo.resumeVo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class PortfolioVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private int resume_po_id;  //pk
	private String resume_po_type;  // 파일 혹은 url F  U
	private String resume_po_url;  // 파일주소 혹은 url주소
	private String usePofolForm;
}
