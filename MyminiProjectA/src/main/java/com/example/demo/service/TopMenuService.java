package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TopMenuDao;
import com.example.demo.vo.UserVo;

@Service
public class TopMenuService {
	
	@Autowired
	private TopMenuDao dao;
	

	// [사람] 메인 리스트
	public List<UserVo> selectUserList(){
		return dao.selectUserList();
	}
}
