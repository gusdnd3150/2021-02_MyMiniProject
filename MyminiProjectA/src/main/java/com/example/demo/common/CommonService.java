package com.example.demo.common;

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
				
				
				loadUserByUsername(selectUser.getUserId());
				System.out.println("나온 후 "+selectUser.toString());
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

	
	@Override
	public UserVo loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVo setUser =new UserVo();
		UserVo user = dao.selectUserById(userId);
		
		setUser.setUsername(user.getUserId());;
		setUser.setPassword(user.getUserPassword());
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getAutho()));

        setUser.setAuthorities(authorities);
        System.out.println("유저권한 설정 후:"+setUser.toString());
		return setUser;
	}
}
