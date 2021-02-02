package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MyPageDao;
import com.example.demo.vo.PagingVo;
import com.example.demo.vo.ResumeVo;

@Service
public class MyPageService {

	@Autowired
	private MyPageDao dao;
	
	public List<ResumeVo> selectResume(PagingVo paging){
		return dao.selectResume(paging);
	}
	
	public int totalUserResume(int id) {
		return dao.totalUserResume(id);
	}
}
