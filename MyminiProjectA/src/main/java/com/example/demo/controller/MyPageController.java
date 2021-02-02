package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.MyPageService;
import com.example.demo.vo.PagingVo;
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

}
