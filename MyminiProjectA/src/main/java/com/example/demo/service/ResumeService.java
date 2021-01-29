package com.example.demo.service;

import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	public String insertResume(ResumeVo resume,MultipartHttpServletRequest upfile,HttpServletRequest request) {
	   String result =null;	
	   int resumeId =0;
	   
	   try {
		   //dao.insertResumeDetail(resume);
		   //resumeId =resume.getResume_id();  //pk값
		   
		   // 학력 등록 
		   if(resume.getUseEducateForm().equals("true")) {
			   
		   }else if(resume.getUseExperienceForm().equals("true")) {
			   System.out.println("경력");
			   
		   }else if(resume.getUselicenseForm().equals("true")) {
			   System.out.println("자격증");
			   
		   }else if(resume.getUseCramForm().equals("true")) {
			   System.out.println("학언");
			   
		   }else if(resume.getUseLanguageForm().equals("true")) {
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
