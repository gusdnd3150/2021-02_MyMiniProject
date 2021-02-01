package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MyPageDao;
import com.example.demo.vo.ResumeVo;

@Service
public class MyPageService {

	@Autowired
	private MyPageDao dao;
	
	public List<ResumeVo> selectResume(int id){
		return dao.selectResume(id);
	}
}
