package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.PagingVo;
import com.example.demo.vo.ResumeVo;

@Repository
public class MyPageDao {
	
	@Autowired
	private SqlSession session;

	public List<ResumeVo> selectResume(PagingVo paging){
		return session.selectList("myPage.selectResume",paging);
	}
	
	public int totalUserResume(int id) {
		return session.selectOne("myPage.totalUserResume",id);
	}
}
