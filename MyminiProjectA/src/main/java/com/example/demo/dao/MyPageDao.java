package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.hireVo.HireApplyVo;
import com.example.demo.hireVo.HireInfoVo;
import com.example.demo.hireVo.HireMultipleVo;
import com.example.demo.hireVo.HireVo;
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
	
	public List<HireVo> selectHireList(){
		return session.selectList("hire.selectHireList");
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
	
	// 채용공고 등록
	public void insertHire(HireVo hire) {
		session.insert("hire.insertHire",hire);
	}
	// 접수방법 등록
	public void insertHireApply(HireApplyVo apply) {
		session.insert("hire.insertHireApply",apply);
	}
	// 모집상세 내용
	public void insertHireInfo(List<HireInfoVo> info) {
		session.insert("hire.insertHireInfo",info);
	}
	// 채용 토탈 개수
	public int totalHireCount(int id) {
		return session.selectOne("hire.totalHireCount",id);
	}
	
	public List<HireVo> selectHireListByPaging(PagingVo paging){
		return session.selectList("hire.selectHireListByPaging",paging);
	}
	
	public int modHireState(HireVo vo) {
		return session.update("hire.modHireState",vo);
	}
}
