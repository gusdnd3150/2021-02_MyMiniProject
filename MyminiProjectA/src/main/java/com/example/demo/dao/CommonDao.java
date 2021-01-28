package com.example.demo.dao;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.UserVo;

@Repository
public class CommonDao {

	
	@Autowired
	private SqlSession session;
	
	
    //유저 정보 가져오기 (vo)
	public UserVo selectUser(UserVo user) {
		return session.selectOne("user.selectUser",user);
	}
	
	//유저 정보 가져오기 (아이디)	
		public UserVo selectUserById(String userId) {
			return session.selectOne("user.selectUserById",userId);
		}
	
	//회원가입   + id PK값 리턴
	public Integer userJoin(UserVo user) {
		return session.insert("user.userJoin",user);
	}
	
	//유저 디테일정보 추가
	public void insertUserDetail(UserVo user) {
		session.insert("user.inserUserDetail",user);
	}
	//회사 디테일정보 추가
	public void insertCompanyDetail(UserVo user) {
		session.insert("user.insertCompanyDetail",user);
	}
	
}

