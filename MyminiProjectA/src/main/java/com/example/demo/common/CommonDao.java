package com.example.demo.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao {

	
	@Autowired
	private SqlSession session;
	
	
    //유저 정보 가져오기	
	public UserVo selectUser(UserVo user) {
		return session.selectOne("user.selectUser",user);
	}
	
	//회원가입
	public Integer userJoin(UserVo user) {
		return session.insert("user.userJoin",user);
	}
	
	
}


