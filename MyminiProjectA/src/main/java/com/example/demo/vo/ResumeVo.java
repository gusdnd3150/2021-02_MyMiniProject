package com.example.demo.vo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class ResumeVo {
	
	//테이블 정보
	//resume
	private int id; //유저 fk
	private int resume_id;   //pk
	private String resume_title;  // 이력서 제목
	private String resume_state;   // 공개여부   "Y","N"
	
	
	//resume_detail   이력서 [인적 상세정보]
	private String resume_name;  // 이름
	private String resume_gender;  // 성별
	private String resume_email;  // 이메일
	private String resume_phone;  // 전화번호
	private String resume_address1;  // 주소	
	
	//resume_cram  [학원/교육]
	private int resume_cr_id;   //pk 
	private String resume_cr_subject; // 학원과목
	private String resume_cr_name;   // 학원이름
	private String resume_cr_start;  // 시작
	private String resume_cr_end;    //종료
	private String resume_cr_content;  //상세내용
	private String useCramForm;      //학원/교육 form 사용여부
	
	// resume_educate    [학력]
	private int resume_ed_id;  //pk
	private String resume_ed_type;  // 2년제 4년제 등
	private String resume_ed_school;  // 대학교
	private String resume_ed_start;  // 입학일
	private String resume_ed_end;  // 졸업일
	private String resume_ed_Gstate;  // 졸업여부 상태
	private String resume_ed_major;  // 전공
	private String resume_ed_score;  // 졸업학점
	private String useEducateForm;   // form 사용여부 기본값은 false
	
	
	//resume_experience    [경력]
	private int resume_ex_id;  //pk
	private String resume_ex_company;  // 회사이름
	private String resume_ex_job;  // 직책
	private String resume_ex_jobType;  // 업무분야
	private String resume_ex_content;  // 업무내용
	private String resume_ex_start;  // 입사일
	private String resume_ex_end;  // 퇴사일
	private String resume_ex_salary;  // 연봉
	private String useExperienceForm;  // form 사용여부 기본값은 fasle
	
	//resume_language  [언어능력]
	private int resume_la_id;  //pk
	private String resume_la_type;  // 언어종류
	private String resume_la_level;  // 언어 숙련도
	private String useLanguageForm;
	
	//resume_license [자격증]
	private int resume_li_id;  //pk
	private String resume_li_name;  // 자격증이름
	private String resume_li_from;  // 발행처
	private String resume_li_getDay;  //취득일
	private String uselicenseForm;   //자격증 form 사용여부 기본값은 false
	
	//resume_portfolio_id [포트폴리오]
	private int resume_po_id;  //pk
	private String resume_po_type;  // 파일 혹은 url F  U
	private String resume_po_url;  // 파일주소 혹은 url주소
	private String usePofolForm;
	
	//resume_selfintro  [자기소개서]
	private String resume_self_content;  // 자기소개서
	
	private List<ResumeVo> list;
}


