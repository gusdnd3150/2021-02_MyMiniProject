package com.example.demo.resumeVo;


import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class ExperienceVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private int resume_ex_id;  //pk
	private String resume_ex_company;  // 회사이름
	private String resume_ex_job;  // 직책
	private String resume_ex_jobType;  // 업무분야
	private String resume_ex_content;  // 업무내용
	private String resume_ex_start;  // 입사일
	private String resume_ex_end;  // 퇴사일
	private int resume_ex_salary;  // 연봉
	private String resume_ex_departName; //부서이름
	private String useExperienceForm;  // form 사용여부 기본값은 fasle
	
}
