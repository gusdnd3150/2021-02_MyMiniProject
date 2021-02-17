package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.UserVo;

@Repository
public class TopMenuDao {
	
	@Autowired
	private SqlSession session;
	
	
	// 사람 메인화면 리스트
	public List<UserVo> selectUserList(){
		return session.selectList("topMenu.selectUserList");
	}

}
