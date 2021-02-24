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

import com.example.demo.hireVo.HireMultipleVo;
import com.example.demo.hireVo.HireVo;
import com.example.demo.resumeVo.ResumeMultiVo;
import com.example.demo.service.ApplyService;
import com.example.demo.service.CommonService;
import com.example.demo.service.MyPageService;
import com.example.demo.service.ResumeService;
import com.example.demo.vo.ApplyVo;
import com.example.demo.vo.MediaVo;
import com.example.demo.vo.MessageVo;
import com.example.demo.vo.PagingVo;
import com.example.demo.vo.PortfolioFileVo;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Controller
public class MyPageController {
	
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private MyPageService service;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ResumeService resumeService;
	
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
		
		
		List<MediaVo> mediaList =service.selectMediaList(user.getId());
		
		model.addAttribute("userDetail", user);
		model.addAttribute("mediaList", mediaList);
		model.addAttribute("resumeList", list);
		
		model.addAttribute("total", total);
		model.addAttribute("paging", paging);
		return "mypage/myPage";
	}
	
	//기업 페이지
	@RequestMapping("/companyPage.do")
	public String companyPage(Model model,HttpServletRequest request) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		
		model.addAttribute("userDetail", user);
		return "companyPage/hirePage";
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
		
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileCount", fileList.size());
		return "mypage/myFileList";
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
	
	
	
	//이력서 상세페이지
	@GetMapping("/resumeDetail.do")
	public String resumeDetail(ResumeVo resume,Model model,HttpServletRequest request) {
		
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		ResumeMultiVo userResume =resumeService.selectResumeDetail(resume);
		
		model.addAttribute("userResume", userResume);
		model.addAttribute("userDetail", user);
		return "mypage/resumeDetail";
	}
	
	
	//1분 자기소개 페이지
		@GetMapping("/oneMinuteIntro.do")
		public String oneMinuteIntro(Model model,HttpServletRequest request) {
			UserVo user= (UserVo) request.getSession().getAttribute("USER");
			
			model.addAttribute("userDetail", user);
			return "mypage/oneMinuteIntro";
		}
		
		
	//1분 자기소개 영상 업로드
	@ResponseBody
	@PostMapping("/uploadOneIntro.do")
	public String uploadOneIntro(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		String result="fail";
		result =service.insertMediaFileIntro(upfile,request);
		return result;
	}
	
	
	//채용 페이지
	@RequestMapping("/hirePage.do")
	public String hirePage(
			@RequestParam(value="nowPage", required=false, defaultValue="1") int nowPage,
			@RequestParam(value="cntPerPage", required=false, defaultValue="5") int cntPage,
			HttpServletRequest request,Model model) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		
		
		int total = service.totalHireCount(user.getId());
		PagingVo paging = new PagingVo(user.getId(),total,nowPage,cntPage);
		
		List<HireVo> hireList= service.selectHireListByPaging(paging);
		
		model.addAttribute("userDetail", user);
		model.addAttribute("hireList", hireList);
		model.addAttribute("paging", paging);
		
		return "companyPage/hirePage";
	}
	
	
	// 채용공고 form 페이지이동
	@RequestMapping("/insertHire.do")
	public String hireForm(HttpServletRequest request,Model model) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		model.addAttribute("userDetail", user);
		
		
		return "companyPage/insertHire";
	}
	
	
	// 채용공고 insert
	@PostMapping("/addHire.do")
	@ResponseBody
	public String insertHire(HireMultipleVo multiple,HttpServletRequest request) {
		String result = "";
		result =service.insertHire(multiple,request);
		return result;
	}
	
	
	@ResponseBody
	@PostMapping("/modHireState.do")
	public String modHireState(HireVo hire) {
		String result =null;
		result =service.modHireState(hire);
		return result;
	}
	
	
	// 1분영상 상세페이지
	@GetMapping("/showMedia.do")
	public String showMedia(@RequestParam int media_id,Model model,HttpServletRequest request) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		
		MediaVo media = service.selectMedia(media_id);
		
		model.addAttribute("userDetail", user);
		model.addAttribute("media", media);
		
		return "mypage/showMedia";
	}
	
	//메시지 함
	@RequestMapping("/myMassage.do")
	public String myMassage(Model model,HttpServletRequest request,
			@RequestParam(value="nowPage", required=false, defaultValue="1") int nowPage,
			@RequestParam(value="cntPerPage", required=false, defaultValue="5") int cntPerPage) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		
		service.updateMessageCheck(user.getId());
		
		int total =service.totalUserMessage(user.getId());
		PagingVo paging = new PagingVo(user.getId(),total,nowPage,cntPerPage);
		List<MessageVo> messageList = service.selectMessageList(paging);
		
		
		model.addAttribute("paging", paging);
		model.addAttribute("userDetail", user);
		model.addAttribute("messageList", messageList);
		
		return "mypage/myMessage";
	}
	
	@PostMapping("/sendMessage.do")
	@ResponseBody
	public String sendMessage(MessageVo message,HttpServletRequest request) {
		String result ="";
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		message.setId(user.getId());
		result =service.insertMessage(message);
		return result;
	}
	
	//지원현황 리스트
	@RequestMapping("/myApplyList.do")
	public String myApplyList(HttpServletRequest request,Model model,
			@RequestParam(value="nowPage", required=false, defaultValue="1") int nowPage,
			@RequestParam(value="cntPerPage", required=false, defaultValue="5") int cntPage) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		model.addAttribute("userDetail", user);
		
		int total =service.applyTotal(user.getId());
		PagingVo paging = new PagingVo(user.getId(),total,nowPage,cntPage);
		List<ApplyVo> applyList =service.selectApplyListByPaging(paging);
		
		model.addAttribute("paging", paging);
		model.addAttribute("applyList", applyList);
		
		return "mypage/myApplyList";
	}
	
	//지원자 리스트 아작스
	@RequestMapping("/applyUserList.do")
	@ResponseBody
	public List<ApplyVo> applyUserList(@RequestParam("hire_id") int hire_id){
		return service.selectApplyListById(hire_id);
	}
	
	@RequestMapping("/interviewResumeDetail.do")
	public String interviewResumeDetail(HttpServletRequest request,Model model
			) {
		UserVo user= (UserVo) request.getSession().getAttribute("USER");
		ResumeVo param = new ResumeVo();
		
		String apply_id = request.getParameter("apply_id");
		
		//ApplyVo applyVo = applyService.selectApplyOne(applyVo);
		
		//ResumeMultiVo resume = resumeService.selectResumeDetail(user);
		
		model.addAttribute("userDetail", user);
		return "apply/interviewResumeDetail";
	}
	
}
