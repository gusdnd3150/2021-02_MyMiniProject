package com.example.demo.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	

	@Autowired
	private CommonDao dao;

	
	public String loginUserCheck(UserVo user,HttpServletRequest request) {
		String result= null;
		HttpSession  session = request.getSession();
		
		try {
			UserVo selectUser= dao.selectUser(user);
			
			if(selectUser==null) {
				result="noId";
			}else if(!selectUser.getPassword().equals(user.getPassword())) {
				result="noMachPassword";
			}else {
				System.out.println(selectUser.toString());
				session.setAttribute("USER", selectUser);
				result="success";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		
		return result;
	}
}
