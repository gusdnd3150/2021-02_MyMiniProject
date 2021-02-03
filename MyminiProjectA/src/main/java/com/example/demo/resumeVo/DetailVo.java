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
	
	private int id; //유저 fk
	private int resume_id;   //pk
	private String resume_title;  // 이력서 제목
	private String resume_state;   // 공개여부   "Y","N"
	private String resume_update;
}
