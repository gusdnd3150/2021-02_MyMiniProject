package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.hireVo.HireMultipleVo;
import com.example.demo.hireVo.HireVo;
import com.example.demo.service.ApiService;
import com.example.demo.service.CommonService;
import com.example.demo.service.MyPageService;
import com.example.demo.service.ResumeService;
import com.example.demo.vo.ApplyVo;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.SeoulJobInfoVo;
import com.example.demo.vo.UserVo;

import lombok.extern.java.Log;


@Controller
public class CommonController {
	
	@Autowired
	CommonService service;
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	ResumeService resumeService;
	
	@Autowired
	MyPageService myPageService;
	
	//메인화면
	@RequestMapping("/main.do")
	public String main(Model model) {
		
		List<ResumeVo> sickJobList = service.sickJobList(); //9개만
		List<SeoulJobInfoVo> seoulJobList= apiService.getSeoulList();
		List<HireVo> hireList = myPageService.selectHireList();
		
		model.addAttribute("hireList", hireList);
		model.addAttribute("seoulJobList", seoulJobList);
		model.addAttribute("sickJobList", sickJobList);
		
		return "main";
	}
	
	//로그인폼
	@RequestMapping("/loginForm.do")
	public String loginForm() {
		return "user/loginForm";
	}
	
	//유저 로그인
	@PostMapping("/userLogin.do")
	@ResponseBody
	public String login(UserVo user,HttpServletRequest request) {
				String result=null;
				result= service.loginUserCheck(user, request);
				return result;
	}
	
	//재능 등록
	@GetMapping("/addConfidence.do")
	public String confidence() {
		
		return "addCondfidence";
	}
	
	// 가입페이지 이동
	@RequestMapping("/joinForm.do")
	public String joinform(@RequestParam(value="type",required=false,defaultValue="user")String type) {
		
		if(type.equals("user")) {
			return "user/joinForm";
		}else if(type.equals("company")) {
			return "user/joinCompanyForm";
		}
		return "user/joinForm";
	}
	
	
	// 기업,유저 가입 페이지로 이동
	@RequestMapping("/chooseJoinType.do")
	public String joinTypePage() {
		return "user/chooseJoinType";
	}
	
	// 회원가입
	@ResponseBody
	@RequestMapping("/userJoin.do")
	public String joinUser(UserVo user,MultipartHttpServletRequest upfile,HttpServletRequest request) {
		System.out.println("컨트롤러:"+user.toString());
		String result=null;
		result = service.userJoin(user,upfile,request);
		return result;
	}
	
	
	//로그아웃
	@RequestMapping("/logOutUser.do")
	public String logout(HttpSession session,HttpServletRequest request) {
		session.removeAttribute("USER");
		return "main";
	}
	
	
	//유저아이디 중복체크
	@ResponseBody
	@PostMapping("/checkId.do")
	public String checkDup(@RequestParam("user_id")String user_id) {
		String result = service.checkDup(user_id);
		return result;
	}
	
	// 채용 상세페이지
	@RequestMapping("/hireDetails.do")
	public String hireDetails(//@RequestParam("hire_id") int hire_id
			ApplyVo applvo,Model model,HttpServletRequest request) {
		
		UserVo user = (UserVo) request.getSession().getAttribute("USER");
		int count =0;
		
		if(user != null) {
			applvo.setId(user.getId());
			count =service.checkApplyInfo(applvo);
		}
		HireMultipleVo hireDetail= service.selectHire(applvo.getHire_id()); // 디테일정보
		int applyTotal = service.applyTotal(applvo.getHire_id()); //지원자 총 수
		
		model.addAttribute("applyTotal", applyTotal);
		model.addAttribute("checkApply", count);
		model.addAttribute("hireDetail", hireDetail);
		return "companyPage/hireDetails";
	}
	
	@PostMapping("/checkResumeList.do")
	@ResponseBody
	public List<ResumeVo> checkResumeList(HttpServletRequest request) {
		UserVo user = (UserVo) request.getSession().getAttribute("USER");
		return resumeService.selectResumeList(user.getId());
	}
	
	//이력서 지원 insert
	@PostMapping("/insertApply.do")
	@ResponseBody
	public String insertApply(ApplyVo applyVo,HttpServletRequest request) {
		UserVo user = (UserVo) request.getSession().getAttribute("USER");
		applyVo.setId(user.getId());
		return service.insertApply(applyVo);
	}
}
