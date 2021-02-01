package com.example.demo.service;

import java.util.ArrayList;
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
	
	//@Autowired
	//private ResumeFunction resumeFunction;
	
	
	public UserVo selectUserDetail(UserVo user) {
		return dao.selectDefualtInfoById(user.getId());
	}
	
	// 이력서 등록
	@Transactional
	public String insertResume(String info,HttpServletRequest request) {
	   String result =null;	
	   int resumeId =0;
	   //HttpSession session= request.getSession();
	   UserVo user= (UserVo) request.getSession().getAttribute("USER");
	   ResumeVo resume = new ResumeVo();
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
	   
	   resume.setId(user.getId());
	   resume.setResume_title(detail.get("resume_title").getAsString());
	   resume.setResume_state("N");
	   
	   
	   try {
		   dao.insertResumeDetail(resume);
		   resumeId =resume.getResume_id();  //pk값
		   
		   
		   if(edu.get("useEducateForm").getAsString().equals("true")) {
			   insertEducate(edu,resumeId);
			   
		   } 
		   if(experience.get("useExperienceForm").getAsString().equals("true")) {
			   insertExperience(experience, resumeId);
		   } 
		   if(license.get("uselicenseForm").getAsString().equals("true")) {
			   
		   } 
		   if(pofol.get("usePofolForm").getAsString().equals("true")) {
			   
		   }
		   
		   if(cram.get("useCramForm").getAsString().equals("true")) {
			   
		   } 
		   
		   if(language.get("useLanguageForm").getAsString().equals("true")) {
			   
		   }
		   result ="success";
	} catch (Exception e) {
		e.printStackTrace();
		result="fail";
	}
	   
	   
		return result;
		
	}
	
	
	
	   //학력 insert
		public void insertEducate(JsonObject edu, int resumeId) {
			ResumeVo educate = null;
			List<ResumeVo> educateList= new ArrayList<>();
			
			if(edu.get("resume_ed_type") instanceof JsonArray ) {
				JsonArray types = edu.getAsJsonArray("resume_ed_type");
				JsonArray schools = edu.getAsJsonArray("resume_ed_school");
				JsonArray starts = edu.getAsJsonArray("resume_ed_start");
				JsonArray ends = edu.getAsJsonArray("resume_ed_end");
				JsonArray Gstates = edu.getAsJsonArray("resume_ed_Gstate");
				JsonArray majors = edu.getAsJsonArray("resume_ed_major");
				JsonArray scores = edu.getAsJsonArray("resume_ed_score");
				
				for(int i=0;i<types.size();i++) {
					educate =new ResumeVo();
					educate.setResume_id(resumeId);
					educate.setResume_ed_type(types.get(i).getAsString());
					educate.setResume_ed_school(schools.get(i).getAsString());
					educate.setResume_ed_start(starts.get(i).getAsString());
					educate.setResume_ed_end(ends.get(i).getAsString());
					educate.setResume_ed_Gstate(Gstates.get(i).getAsString());
					educate.setResume_ed_major(majors.get(i).getAsString());
					educate.setResume_ed_score(scores.get(i).getAsString());
					educateList.add(educate);
				}
			}else {
				educate = new ResumeVo();
				educate.setResume_id(resumeId);
				educate.setResume_ed_type(edu.get("resume_ed_type").getAsString());
				educate.setResume_ed_school(edu.get("resume_ed_school").getAsString());
				educate.setResume_ed_start(edu.get("resume_ed_start").getAsString());
				educate.setResume_ed_end(edu.get("resume_ed_end").getAsString());
				educate.setResume_ed_Gstate(edu.get("resume_ed_Gstate").getAsString());
				educate.setResume_ed_major(edu.get("resume_ed_major").getAsString());
				educate.setResume_ed_score(edu.get("resume_ed_score").getAsString());
				educateList.add(educate);
			}
			dao.insertResumeEducate(educateList);
		}
	
		
		 //경력 insert
		public void insertExperience(JsonObject experience, int resumeId) {
			ResumeVo resumeInfo = null;
			List<ResumeVo> experienceList= new ArrayList<>();
			
			if(experience.get("resume_ex_company") instanceof JsonArray ) {
				JsonArray company = experience.getAsJsonArray("resume_ex_company");
				JsonArray jobs = experience.getAsJsonArray("resume_ex_job");
				JsonArray jobTypes = experience.getAsJsonArray("resume_ex_jobType");
				JsonArray contents = experience.getAsJsonArray("resume_ex_content");
				JsonArray start = experience.getAsJsonArray("resume_ex_start");
				JsonArray end = experience.getAsJsonArray("resume_ex_end");
				JsonArray salary = experience.getAsJsonArray("resume_ex_salary");
				
				for(int i=0;i<company.size();i++) {
					resumeInfo =new ResumeVo();
					resumeInfo.setResume_id(resumeId);
					resumeInfo.setResume_ex_company(company.get(i).getAsString());
					resumeInfo.setResume_ex_job(jobs.get(i).getAsString());
					resumeInfo.setResume_ex_jobType(jobTypes.get(i).getAsString());
					resumeInfo.setResume_ex_content(contents.get(i).getAsString());
					resumeInfo.setResume_ex_start(start.get(i).getAsString());
					resumeInfo.setResume_ex_end(end.get(i).getAsString());
					resumeInfo.setResume_ex_salary(salary.get(i).getAsInt());
					experienceList.add(resumeInfo);
				}
			}else {
				resumeInfo = new ResumeVo();
				resumeInfo.setResume_id(resumeId);
				resumeInfo.setResume_ex_company(experience.get("resume_ex_company").getAsString());
				resumeInfo.setResume_ex_job(experience.get("resume_ex_job").getAsString());
				resumeInfo.setResume_ex_jobType(experience.get("resume_ex_jobType").getAsString());
				resumeInfo.setResume_ex_content(experience.get("resume_ex_content").getAsString());
				resumeInfo.setResume_ex_start(experience.get("resume_ex_start").getAsString());
				resumeInfo.setResume_ex_end(experience.get("resume_ex_end").getAsString());
				resumeInfo.setResume_ex_salary(experience.get("resume_ex_salary").getAsInt());
				experienceList.add(resumeInfo);
			}
			dao.insertResumeExperience(experienceList);
		}
}
