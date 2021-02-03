package com.example.demo.resumeVo;


import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class EducateVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private int resume_ed_id;  //pk
	private String resume_ed_type;  // 2년제 4년제 등
	private String resume_ed_school;  // 대학교
	private String resume_ed_start;  // 입학일
	private String resume_ed_end;  // 졸업일
	private String resume_ed_Gstate;  // 졸업여부 상태
	private String resume_ed_major;  // 전공
	private String resume_ed_score;  // 졸업학점
	private String useEducateForm;   // form 사용여부 기본값은 false
	
}
