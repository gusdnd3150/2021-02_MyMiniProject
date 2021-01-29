package com.example.demo.service;

import java.util.ArrayList;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.CommonDao;
import com.example.demo.file.FileService;
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
				session.setAttribute("USER", selectUser);
				result="success";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		
		return result;
	}
	
	// 구직자 회원가입
	public String userJoin(UserVo user) {
		
		String result=null;
		try {
			
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
				    user.setUser_password(encoder.encode(user.getUser_password()));
					int id=dao.userJoin(user);   // users 기본정보만 insert
					dao.insertUserDetail(user);  
				  result="success";
			}else if(selectUser.getUser_id().equals(user.getUser_id())){
				result="already";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		} 
		return result; 
	}
	//기업 회원가입
	public String CompanyJoin(UserVo user,MultipartHttpServletRequest upfile,HttpServletRequest request) {
		
		String result=null;
		try {
			
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
				user.setUser_password(encoder.encode(user.getUser_password()));
					user.setCompany_logo(fileService.addImageFile(upfile, request));
					int id=dao.userJoin(user);   // users 기본정보만 insert
					dao.insertCompanyDetail(user);
				result="success";
			}else if(selectUser.getUser_id().equals(user.getUser_id())){
				result="already";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		} 
		return result; 
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

	
	//보류
	@Override
	public UserSecurityVo loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("--" + userId);
		UserVo user = dao.selectUserById(userId);
		UserSecurityVo securityVo = new UserSecurityVo(user);
		if(user.getUser_password().equals(securityVo.getPassword())) {
			System.out.println("비번같음");
		}else {
			System.out.println("틀림");
		}
		System.out.println("password: "+securityVo.getPassword()+" 이름:"+securityVo.getUsername()+" 권한:"+securityVo.getAuthorities());
		return securityVo;
	}
}
