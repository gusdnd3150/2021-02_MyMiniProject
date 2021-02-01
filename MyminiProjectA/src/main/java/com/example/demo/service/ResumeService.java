package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.ResumeDao;
import com.example.demo.file.FileService;
import com.example.demo.vo.PortfolioFileVo;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao dao;
	
	@Autowired
	private FileService fileService;
	
	public UserVo selectUserDetail(UserVo user) {
		return dao.selectDefualtInfoById(user.getId());
	}
	
	public List<PortfolioFileVo> selectUserFile(UserVo user) {
		return dao.selectUserFile(user.getId());
	}
	
	public String insertImage(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		String image=null;
		try {
			 image =fileService.insertProfile(upfile, request);
			
		} catch (Exception e) {
			e.printStackTrace();
			image="";
		}
		
		return image;
	}
	
	// 이력서 등록
	@Transactional(propagation = Propagation.REQUIRED)
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
		   dao.insertResume(resume);  // resume 테이블에  insert
		   resumeId =resume.getResume_id();  //pk
		   
		   insertDetail(detail,resumeId,request);   //인적사항  insert
		   insertSelfInfo(selfIntro,resumeId); //자기소개서 insert
		   
		   // 학력
		   if(edu.get("useEducateForm").getAsString().equals("true")) {
			   insertEducate(edu,resumeId);
		   } 
		   //경력
		   if(experience.get("useExperienceForm").getAsString().equals("true")) {
			   insertExperience(experience, resumeId);
		   } 
		   //자격증
		   if(license.get("uselicenseForm").getAsString().equals("true")) {
			   insertLicense(license, resumeId);
		   } 
		   //포폴
		   if(pofol.get("usePofolForm").getAsString().equals("true")) {
			   insertPofol(pofol,resumeId);
		   }
		   
		   //학원 등 이력
		   if(cram.get("useCramForm").getAsString().equals("true")) {
			   insertCram(cram,resumeId);
		   } 
		   // 언어능력
		   if(language.get("useLanguageForm").getAsString().equals("true")) {
			   insertLanguage(language,resumeId);
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
				JsonArray departName = experience.getAsJsonArray("resume_ex_departName");
				
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
					resumeInfo.setResume_ex_departName(departName.get(i).getAsString());
					
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
				resumeInfo.setResume_ex_departName(experience.get("resume_ex_departName").getAsString());
				experienceList.add(resumeInfo);
			}
			dao.insertResumeExperience(experienceList);
		}
		
		
		
		//자격증 insert
				public void insertLicense(JsonObject license, int resumeId) {
					ResumeVo resumeInfo = null;
					List<ResumeVo> licenseList= new ArrayList<>();
					
					if(license.get("resume_li_name") instanceof JsonArray ) {
						JsonArray names = license.getAsJsonArray("resume_li_name");
						JsonArray froms = license.getAsJsonArray("resume_li_from");
						JsonArray getDay = license.getAsJsonArray("resume_li_getDay");
						
						for(int i=0;i<names.size();i++) {
							resumeInfo =new ResumeVo();
							resumeInfo.setResume_id(resumeId);
							resumeInfo.setResume_li_name(names.get(i).getAsString());
							resumeInfo.setResume_li_from(froms.get(i).getAsString());
							resumeInfo.setResume_li_getDay(getDay.get(i).getAsString());
							licenseList.add(resumeInfo);
						}
					}else {
						resumeInfo = new ResumeVo();
						resumeInfo.setResume_id(resumeId);
						resumeInfo.setResume_li_name(license.get("resume_li_name").getAsString());
						resumeInfo.setResume_li_from(license.get("resume_li_from").getAsString());
						resumeInfo.setResume_li_getDay(license.get("resume_li_getDay").getAsString());
						licenseList.add(resumeInfo);
					}
					dao.insertResumeLicense(licenseList);
				}
				
				
				//이력서 인적사항 insert
				public void insertDetail(JsonObject detail, int resumeId,HttpServletRequest request) {
					ResumeVo resumeInfo = new ResumeVo();
					HttpSession session= request.getSession();
					System.out.println("디테일:"+detail.toString());
					resumeInfo.setResume_id(resumeId);
					resumeInfo.setResume_name(detail.get("resume_name").getAsString());
					resumeInfo.setResume_gender(detail.get("resume_gender").getAsString());
					resumeInfo.setResume_email(detail.get("resume_email").getAsString());
					resumeInfo.setResume_phone(detail.get("resume_phone").getAsString());
					resumeInfo.setResume_address1(detail.get("resume_address1").getAsString());
					resumeInfo.setResume_profile((String) request.getAttribute("tempImage"));
					
					dao.insertResumeDetial(resumeInfo);
				}
				
				//이력서 자기소개서 insert
				public void insertSelfInfo(JsonObject selfinfo, int resumeId) {
					ResumeVo resumeInfo = new ResumeVo();
					
					resumeInfo.setResume_id(resumeId);
					resumeInfo.setResume_self_content(selfinfo.get("resume_self_content").getAsString());
					
					dao.insertResumeSelfInfo(resumeInfo);
				}
				
				//이력서 포트폴리오 insert
				public void insertPofol(JsonObject pofol, int resumeId) {
					ResumeVo resumeInfo = null;
					List<ResumeVo> pofolList= new ArrayList<>();
					
					
					if(pofol.get("resume_po_type") instanceof JsonArray ) {
						JsonArray types = pofol.getAsJsonArray("resume_po_type");
						JsonArray urls = pofol.getAsJsonArray("resume_po_url");
						
						for(int i=0;i<types.size();i++) {
							resumeInfo =new ResumeVo();
							resumeInfo.setResume_id(resumeId);
							resumeInfo.setResume_po_type(types.get(i).getAsString());
							resumeInfo.setResume_po_url(urls.get(i).getAsString());
							pofolList.add(resumeInfo);
						}
					}else {
						resumeInfo = new ResumeVo();
						resumeInfo.setResume_id(resumeId);
						resumeInfo.setResume_po_type(pofol.get("resume_po_type").getAsString());
						resumeInfo.setResume_po_url(pofol.get("resume_po_url").getAsString());
						pofolList.add(resumeInfo);
					}
					dao.insertResumePortfolio(pofolList);
				}
				
				//이력서 교육,학원등  insert
				public void insertCram(JsonObject cram, int resumeId) {
					ResumeVo resumeInfo = null;
					List<ResumeVo> cramlList= new ArrayList<>();
					
					
					if(cram.get("resume_cr_subject") instanceof JsonArray ) {
						JsonArray subjects = cram.getAsJsonArray("resume_cr_subject");
						JsonArray names = cram.getAsJsonArray("resume_cr_name");
						JsonArray start = cram.getAsJsonArray("resume_cr_start");
						JsonArray end = cram.getAsJsonArray("resume_cr_end");
						JsonArray contents = cram.getAsJsonArray("resume_cr_content");
						
						
						for(int i=0;i<subjects.size();i++) {
							resumeInfo =new ResumeVo();
							resumeInfo.setResume_id(resumeId);
							
							resumeInfo.setResume_cr_subject(subjects.get(i).getAsString());
							resumeInfo.setResume_cr_name(names.get(i).getAsString());
							resumeInfo.setResume_cr_start(start.get(i).getAsString());
							resumeInfo.setResume_cr_end(end.get(i).getAsString());
							resumeInfo.setResume_cr_content(contents.get(i).getAsString());
							cramlList.add(resumeInfo);
						}
					}else {
						resumeInfo = new ResumeVo();
						resumeInfo.setResume_id(resumeId);
						resumeInfo.setResume_cr_subject(cram.get("resume_cr_subject").getAsString());
						resumeInfo.setResume_cr_name(cram.get("resume_cr_name").getAsString());
						resumeInfo.setResume_cr_start(cram.get("resume_cr_start").getAsString());
						resumeInfo.setResume_cr_end(cram.get("resume_cr_end").getAsString());
						resumeInfo.setResume_cr_content(cram.get("resume_cr_content").getAsString());
						cramlList.add(resumeInfo);
					}
					dao.insertResumeCram(cramlList);
				}
				
				
				//이력서 언어능력  insert
				public void insertLanguage(JsonObject language, int resumeId) {
					ResumeVo resumeInfo = null;
					List<ResumeVo> languageList= new ArrayList<>();
					
					
					if(language.get("resume_la_type") instanceof JsonArray ) {
						JsonArray types = language.getAsJsonArray("resume_la_type");
						JsonArray levels = language.getAsJsonArray("resume_la_level");
						
						for(int i=0;i<types.size();i++) {
							resumeInfo =new ResumeVo();
							resumeInfo.setResume_id(resumeId);
							resumeInfo.setResume_la_type(types.get(i).getAsString());
							resumeInfo.setResume_la_level(levels.get(i).getAsString());
							languageList.add(resumeInfo);
						}
					}else {
						resumeInfo = new ResumeVo();
						resumeInfo.setResume_id(resumeId);
						resumeInfo.setResume_la_type(language.get("resume_la_type").getAsString());
						resumeInfo.setResume_la_level(language.get("resume_la_level").getAsString());
						languageList.add(resumeInfo);
					}
					dao.insertResumeLanguage(languageList);
				}
}
