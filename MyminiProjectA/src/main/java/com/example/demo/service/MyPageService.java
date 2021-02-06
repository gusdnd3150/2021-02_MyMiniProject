package com.example.demo.service;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.MyPageDao;
import com.example.demo.file.FileService;
import com.example.demo.vo.MediaVo;
import com.example.demo.vo.PagingVo;
import com.example.demo.vo.PortfolioFileVo;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Service
public class MyPageService {

	@Autowired
	private MyPageDao dao;
	@Autowired
	private FileService fileService;
	
	
	public List<ResumeVo> selectResume(PagingVo paging){
		return dao.selectResume(paging);
	}
	
	public int totalUserResume(int id) {
		return dao.totalUserResume(id);
	}
	
	//이력서 상태전환
	public String modResumeState(ResumeVo resume) {
		String result =null;
		int value=0;
		try {
			value=dao.modResumeState(resume);
			if(value > 0) {
				result="success";
			}else {
				result="fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}
	
	//이력서 삭제
	public String deleteResume(int resume_id) {
		String result =null;
		try {
			dao.deleteResume(resume_id);
		    result="success";
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}
	
	// 파일리스트
	public List<PortfolioFileVo> selectFileList(int id){
		 List<PortfolioFileVo> list =dao.selectFileList(id);
				 
		return list;
		
	}
	
	//구직자 파일 insert
	public String insertFileUserFile(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		String result="";
		PortfolioFileVo fileVo= new PortfolioFileVo();
		
		try {
			fileVo = fileService.uploadUserFile(upfile,request);
			
		    System.out.println("업로드 후 : "+fileVo.toString());
		    
		    dao.insertFileUserFile(fileVo);
		    result="success";
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}
	
	//파일삭제
	public String deleteFileUserFile(PortfolioFileVo fileVo,HttpServletRequest request) {
		String result ="";
		try {
			
			fileService.deleteFile(fileVo,request); //파일삭제
			dao.deleteFileUserFile(fileVo);
			
			result="success";
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		
		return result;
	}
	
	// 다운로드
	public void downLoadFile(PortfolioFileVo fileVo,HttpServletRequest request
			,HttpServletResponse response) {
		try {
			fileService.downloadFile(fileVo,request,response); //파일삭제
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String insertMediaFileIntro(MultipartHttpServletRequest file,HttpServletRequest request) {
		String result =null;
		MediaVo media =null; 
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		
		try {
			media =fileService.insertMediaFileIntro(file, request);
			media.setId(user.getId());
			
			dao.insertMedia(media);
			result="success";
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		
		
		return result;
	}
	
	
}
