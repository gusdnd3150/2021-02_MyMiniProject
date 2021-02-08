package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.MediaVo;
import com.example.demo.vo.PagingVo;
import com.example.demo.vo.PortfolioFileVo;
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
	
	public int modResumeState(ResumeVo resume) {
		return session.update("myPage.modResumeState",resume);
	}
	
	public void deleteResume(int resume_id) {
		session.delete("myPage.deleteResume",resume_id);
	}
	
	public List<PortfolioFileVo> selectFileList(int id){
		return session.selectList("myPage.selectFileList",id);
	}
	
	public void insertFileUserFile(PortfolioFileVo filevo) {
		session.insert("myPage.insertFileUserFile",filevo);
	}
	
	//파일삭제
	public void deleteFileUserFile(PortfolioFileVo fileVo) {
		session.delete("myPage.deleteFileUserFile",fileVo);
	}
	
	//1분 동영상 insert
	public void insertMedia(MediaVo media) {
		session.insert("myPage.insertMedia",media);
	}
	
	//1분영상 list
	public List<MediaVo> selectMediaList(int id){
		return session.selectList("myPage.selectMediaList",id);
	}
}
