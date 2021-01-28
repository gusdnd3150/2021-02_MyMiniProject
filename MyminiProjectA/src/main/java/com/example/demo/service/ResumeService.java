package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.CommonDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao dao;
	
	public UserVo selectUserDetail(UserVo user) {
		return dao.selectDefualtInfoById(user.getId());
	}
	
	
	
}
