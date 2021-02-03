package com.example.demo.resumeVo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class LicenceVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private int resume_li_id;  //pk
	private String resume_li_name;  // 자격증이름
	private String resume_li_from;  // 발행처
	private String resume_li_getDay;  //취득일
	private String uselicenseForm;   //자격증 form 사용여부 기본값은 false
	
}
