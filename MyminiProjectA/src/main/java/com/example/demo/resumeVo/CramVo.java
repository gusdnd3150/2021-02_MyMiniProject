package com.example.demo.resumeVo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.ResumeVo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class CramVo {
	
	private int id; //유저 fk
	private int resume_id;   //fk
	private int resume_cr_id;   //pk 
	private String resume_cr_subject; // 학원과목
	private String resume_cr_name;   // 학원이름
	private String resume_cr_start;  // 시작
	private String resume_cr_end;    //종료
	private String resume_cr_content;  //상세내용
	private String useCramForm;      //학원/교육 form 사용여부
}
