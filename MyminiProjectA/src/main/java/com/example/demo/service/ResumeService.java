package com.example.demo.service;

import java.util.List;


import com.google.gson.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ResumeDao;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao dao;
	
	
	public UserVo selectUserDetail(UserVo user) {
		return dao.selectDefualtInfoById(user.getId());
	}
	
	
	
	// 이력서 등록
	@Transactional
	public String insertResume(String info) {
	   String result =null;	
	   int resumeId =0;
	   
	   JsonParser jsonParser = new JsonParser();
	   JsonArray jsonArray = (JsonArray) jsonParser.parse(info);
	   
	   JsonObject detail = (JsonObject) jsonArray.get(0); //인적사항
	   JsonObject edu = (JsonObject) jsonArray.get(1); //학력
	   JsonObject experience = (JsonObject) jsonArray.get(2); // 경력
	   JsonObject license = (JsonObject) jsonArray.get(3); // 자격증
	   JsonObject pofol = (JsonObject) jsonArray.get(4); // 포폴
	   JsonObject cram = (JsonObject) jsonArray.get(5); // 교육
	   JsonObject language = (JsonObject) jsonArray.get(6); // 언어능력
	   JsonObject selfIntro = (JsonObject) jsonArray.get(7);  //자기소개
	   
	   
	   try {
		   dao.insertResumeDetail(resume);
		   resumeId =resume.getResume_id();  //pk값
		   
		   
		   if(edu.get("useEducateForm").getAsString().equals("true")) {
			   System.out.println("학력");
			   
		   } 
		   if(experience.get("useExperienceForm").getAsString().equals("true")) {
			   System.out.println("경력");
			   
		   } 
		   if(license.get("uselicenseForm").getAsString().equals("true")) {
			   System.out.println("자격증");
			   
		   } 
		   if(pofol.get("usePofolForm").getAsString().equals("true")) {
			   System.out.println("포폴");
			   
		   }
		   
		   if(cram.get("useCramForm").getAsString().equals("true")) {
			   System.out.println("학원교육");
		   } 
		   if(language.get("useLanguageForm").getAsString().equals("true")) {
			   System.out.println("언어능력");
		   }
		   result ="success";
	} catch (Exception e) {
		e.printStackTrace();
		result="fail";
	}
	   
	   
		return result;
		
	}
	
	
}
