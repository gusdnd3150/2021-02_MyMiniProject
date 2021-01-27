package com.example.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Repository
public class ResumeDao {
	
	@Autowired
	private SqlSession session;
	

	public UserVo selectDefualtInfoById(int id) {
		return session.selectOne("resume.selectDefualtInfoById",id);
	}
	


}
