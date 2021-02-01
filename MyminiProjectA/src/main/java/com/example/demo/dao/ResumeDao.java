package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.ResumeVo;
import com.example.demo.vo.UserVo;

@Repository
public class ResumeDao {
	
	@Autowired
	private SqlSession session;
	
	//유저 디테일 select
	public UserVo selectDefualtInfoById(int id) {
		return session.selectOne("resume.selectDefualtInfoById",id);
	}
	
	// 이력서 insert
	public void insertResume(ResumeVo resume) {
		session.insert("resume.insertResume",resume);
	}
	// 이력서 자기소개서 insert
	public void insertResumeSelfInfo(ResumeVo resume) {
		session.insert("resume.insertResumeSelfInfo",resume);
	}
	
	// 이력서 인적사항
	public void insertResumeDetial(ResumeVo resume) {
		session.insert("resume.insertResumeDetail",resume);
	}

	// 이력서 학력 insert
	public void insertResumeEducate(List<ResumeVo> list) {
		session.insert("resume.insertResumeEducate",list);
		
	}
	
	// 이력서 경력 insert
	public void insertResumeExperience(List<ResumeVo> list) {
		session.insert("resume.insertResumeExperience",list);
			
	}
		
	//이력서 자격증 insert
	public void insertResumeLicense(List<ResumeVo> list) {
		session.insert("resume.insertResumeLicense",list);
	}
	// 이력서 포트폴리오
	public void insertResumePortfolio(List<ResumeVo> list) {
		session.insert("resume.insertResumePortfolio",list);
	}
	// 이력서 학원등
	public void insertResumeCram(List<ResumeVo> list) {
		session.insert("resume.insertResumeCram",list);
	}
	
	// 이력서 언어능력
	public void insertResumeLanguage(List<ResumeVo> list) {
		session.insert("resume.insertResumeLanguage",list);
	}
	
}
