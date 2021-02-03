package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.service.MyPageService;
import com.example.demo.vo.PagingVo;
import com.example.demo.vo.PortfolioFileVo;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Controller
public class MyPageController {
	
	
	@Autowired
	private MyPageService service;
	
	
	//마이페이지 이동 기본은 이력서 페이지
	@RequestMapping("/myPage.do")
	public String mypage(
			@RequestParam(value="nowPage", required=false, defaultValue="1") int nowPage,
			@RequestParam(value="cntPerPage", required=false, defaultValue="5") int cntPage,
			Model model,HttpServletRequest request) {
		
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		int total =service.totalUserResume(user.getId());
		
		PagingVo paging = new PagingVo(user.getId(),total,nowPage,cntPage);
		List<ResumeVo> list= service.selectResume(paging);
		
		model.addAttribute("total", total);
		model.addAttribute("paging", paging);
		model.addAttribute("resumeList", list);
		model.addAttribute("userDetail", user);
				
		return "/mypage/myPage";
	}
	
	@ResponseBody
	@PostMapping("/modResumeState.do")
	public String modResumeState(ResumeVo resume) {
		String result=null;
		result =service.modResumeState(resume);
		return result;
	}
	
	@ResponseBody
	@PostMapping("/deleteResume.do")
	public String deleteResume(@RequestParam("resume_id") int resume_id) {
		String result=null;
		result =service.deleteResume(resume_id);
		return result;
	}
	
	//파일 보관함 이동
	@RequestMapping("/myfileList.do")
	public String myFileList(Model model,HttpServletRequest request) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		model.addAttribute("userDetail", user);
		
		List<PortfolioFileVo> fileList =service.selectFileList(user.getId());
		
		System.out.println("파일:"+fileList);
		
		model.addAttribute("fileList", fileList);
		return "/mypage/myFileList";
	}
	
	
	// 구직자 개인 파일 업로드
	@ResponseBody
	@PostMapping("/uploadUserFile.do")
	public String uploadUserFile(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		String result ="";

		result =service.insertFileUserFile(upfile,request);
		return result;
	}

	
	// 구직자 개인 파일 삭제
	@ResponseBody
	@PostMapping("/deletePofolFile.do")
	public String deletePofolFile(PortfolioFileVo fileVo,HttpServletRequest request) {
		String result ="";
		result =service.deleteFileUserFile(fileVo,request);
		return result;
	}
	
	@GetMapping("/downLoadPofolFile.do")
	public void downLoadPofolFile(PortfolioFileVo fileVo,HttpServletRequest request,
			HttpServletResponse response) {
		String result ="";
		
		service.downLoadFile(fileVo,request,response);
	}
	
	
	
}
