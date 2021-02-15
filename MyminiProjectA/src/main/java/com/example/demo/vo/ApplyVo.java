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

	
	// hire 테이블 정보
	private String hire_title; // 제목
	private String hire_open;  //공개여부
	private String hire_condition; //경력조건
	private String hire_educate; // 학력
	private String hire_state;  //고용형태
	private String hire_salary; // 급여
	private String hire_location; // 회사위치 
	private String hire_workTime; // 주5일 등등
	private String hire_rank;  // 직급   사원, 등
	
	private String hire_address1;  // 주소
	private String hire_address2;  // 상세주소
	private String hire_zip;  // 우편
	
	
	//hire_apply 테이블 정보
	private String hire_apply_charge; //채용 담당자
	private String hire_apply_department; //담당자 부서
	private String hire_apply_phone;  // 연락처
	private String hire_apply_time; //접수기간 (마감일)
	private String hire_apply_way; //접수방버 (이력서 / 이력서+ 1분자기소개)
	private String hire_apply_p2p; // 1:1화살면접 여부   Y ,N
	
	
	// resume 테이블 정보
	//테이블 정보
		//resume
		private String resume_title;  // 이력서 제목
		private String resume_state;   // 공개여부   "Y","N"
		private String resume_update;
		
		//resume_detail   이력서 [인적 상세정보]
		private String resume_name;  // 이름
		private String resume_gender;  // 성별
		private String resume_email;  // 이메일
		private String resume_phone;  // 전화번호
		private String resume_address1;  // 주소	
		private String resume_profile; //이미지
		
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
		private int resume_ex_salary;  // 연봉
		private String resume_ex_departName; //부서이름
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
	
}
