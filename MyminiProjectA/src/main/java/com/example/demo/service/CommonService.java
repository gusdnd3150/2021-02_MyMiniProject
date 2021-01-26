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

import com.example.demo.common.CommonDao;
import com.example.demo.common.UserSecurityVo;
import com.example.demo.common.UserVo;

@Service
public class CommonService  implements UserDetailsService{
	
	@Autowired
	private CommonDao dao;

	@Autowired
	PasswordEncoder encoder;
	
	//로그인
	public String loginUserCheck(UserVo user,HttpServletRequest request) {
		String result= null;
		HttpSession  session = request.getSession();
	
		try {
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
			result="noId";
			}else if(!encoder.matches(user.getUserPassword(), selectUser.getUserPassword())) {
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
	
	//회원가입
	public String userJoin(UserVo user) {
		String result=null;
		System.out.println(user.toString());
		try {
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
				user.setUserPassword(encoder.encode(user.getUserPassword()));
				int id=dao.userJoin(user);
				result="success";
			}else if(selectUser.getUserId().equals(user.getUserId())){
				result="already";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		} 
		return result; 
	}

	
	//보류
	@Override
	public UserSecurityVo loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("--" + userId);
		UserVo user = dao.selectUserById(userId);
		UserSecurityVo securityVo = new UserSecurityVo(user);
		if(user.getUserPassword().equals(securityVo.getPassword())) {
			System.out.println("비번같음");
		}else {
			System.out.println("틀림");
		}
		
		
		System.out.println("password: "+securityVo.getPassword()+" 이름:"+securityVo.getUsername()+" 권한:"+securityVo.getAuthorities());
		return securityVo;
	}
}