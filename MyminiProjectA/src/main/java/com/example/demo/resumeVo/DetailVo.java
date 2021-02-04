package com.example.demo.resumeVo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.ResumeVo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class DetailVo {
	
	
	/* 이력서 */	
	private int id; //유저 fk
	private int resume_id;   //pk
	private String resume_title;  // 이력서 제목
	private String resume_state;   // 공개여부   "Y","N"
	private String resume_update;
	
	
	/* 이력서 인적사항 */
	
	//resume_detail   이력서 [인적 상세정보]
	private String resume_name;  // 이름
	private String resume_gender;  // 성별
	private String resume_email;  // 이메일
	private String resume_phone;  // 전화번호
	private String resume_address1;  // 주소	
	private String resume_profile; //이미지
	
}
