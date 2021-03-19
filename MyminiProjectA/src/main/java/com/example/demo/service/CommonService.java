package com.example.demo.service;

import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.CommonDao;
import com.example.demo.file.FileService;
import com.example.demo.hireVo.HireMultipleVo;
import com.example.demo.vo.ApplyVo;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserSecurityVo;
import com.example.demo.vo.UserVo;


@Service
public class CommonService  implements UserDetailsService{
	
	@Autowired
	private CommonDao dao;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private FileService fileService;
	
	
	// 구직 게시판
	public List<ResumeVo> sickJobList(){
		return dao.sickJobList();
	}
	
	
	//로그인
	public String loginUserCheck(UserVo user,HttpServletRequest request) {
		String result= null;
		HttpSession  session = request.getSession();
	
		try {
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
			result="noId";
			}else if(!encoder.matches(user.getUser_password(), selectUser.getUser_password())) {
				result="noMachPassword";
			}else {
				UserVo loginUser =dao.selectUserWithDetail(selectUser);
				session.setAttribute("USER", loginUser);
				result="success";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		
		return result;
	}
	
	
	//기업 회원가입
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public String userJoin(UserVo user,MultipartHttpServletRequest upfile,HttpServletRequest request) {
		
		String result=null;
		try {
			
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
					user.setUser_password(encoder.encode(user.getUser_password()));
					
					if(user.getAutho().equals("USER")) {  //구직자\
						user.setUser_profile(fileService.addImageFile(upfile, request,"user"));
						insertUser(user);
						
					}else if(user.getAutho().equals("COMPANY")) { 						// 기업
						user.setCompany_logo(fileService.addImageFile(upfile, request,"company"));
						insertCompanyDetail(user);
					}
					
				result="success";
			}else if(selectUser.getUser_id().equals(user.getUser_id())){
				result="already";
			}
			
		} catch (Exception e) {
			result="fail";
			throw new RuntimeException("Exception for rollback");
		}
		
		return result; 
	}
	
	public void insertUser(UserVo user) {
		int id=dao.userJoin(user);
		dao.insertUserDetail(user);
	}
	public void insertCompanyDetail(UserVo user) {
		int id=dao.userJoin(user);
		dao.insertCompanyDetail(user);
	}
	
	//아이디 중복 검사
	public String checkDup(String user_id) {
		String result =null;
		try {
			UserVo user= dao.selectUserById(user_id);
			if(user==null) {
				result="able";
			}else {
				result ="already";
			}
		} catch (Exception e) {
			result="fail";
			e.printStackTrace();
		}
		return result;
	}
	
	public HireMultipleVo selectHire(int hire_id) {
		HireMultipleVo hire=null;
		
		try {
			
			hire= dao.selectHire(hire_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hire; 
	}
	
	public String insertApply(ApplyVo applyVo) {
		String result="";
		int after=0;
		try {
			
			after =dao.insertApply(applyVo);
			System.out.println(after);
			
			if(after > 0) {
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
	
	public int checkApplyInfo(ApplyVo applvo) {
		return dao.checkApplyInfo(applvo);
	}
	
	public int applyTotal(int hire_id) {
		return dao.applyTotal(hire_id);
	}

	
	//보류
	@Override
	public UserSecurityVo loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("--" + userId);
		UserVo user = dao.selectUserById(userId);
		UserSecurityVo securityVo = new UserSecurityVo(user);
		if(user.getUser_password().equals(securityVo.getPassword())) {
		}else {
		}
		System.out.println("password: "+securityVo.getPassword()+" 이름:"+securityVo.getUsername()+" 권한:"+securityVo.getAuthorities());
		return securityVo;
	}
}
