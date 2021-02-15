package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.resumeVo.CramVo;
import com.example.demo.resumeVo.DetailVo;
import com.example.demo.resumeVo.EducateVo;
import com.example.demo.resumeVo.ExperienceVo;
import com.example.demo.resumeVo.LanguageVo;
import com.example.demo.resumeVo.LicenceVo;
import com.example.demo.resumeVo.PortfolioVo;
import com.example.demo.resumeVo.ResumeMultiVo;
import com.example.demo.resumeVo.SelfintroVo;
import com.example.demo.vo.PortfolioFileVo;
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
	public List<PortfolioFileVo> selectUserFile(int id) {
		return session.selectList("resume.selectUserFile",id);
	}
	
	// 이력서 insert
	public void insertResume(ResumeVo resume) {
		session.insert("resume.insertResume",resume);
	}
	
	public void updateUserDetail(UserVo user) {
		session.update("resume.updateUserDetail",user);
		
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
	
	// 이력서 디테일 select
	public ResumeMultiVo selectResumeDetail(ResumeVo resume) {
		ResumeMultiVo multipleVo= new ResumeMultiVo();
		
		DetailVo detail =session.selectOne("resume.selectResumeDetail",resume);  //인적사항
		SelfintroVo selfInfo =session.selectOne("resume.selectResumeSelfInfo",resume);  // 자기소개서
		List<EducateVo> educate =session.selectList("resume.selectResumeEducate",resume);  // 학력
		List<ExperienceVo> experience =session.selectList("resume.selectResumeExperience",resume);  // 경력
		List<LicenceVo> licence =session.selectList("resume.selectResumeLicence",resume);  // 자격증
		List<PortfolioVo> portfolio =session.selectList("resume.selectResumePortfolio",resume);  // 포폴
		List<CramVo> cram =session.selectList("resume.selectResumeCram",resume);  // 교육
		List<LanguageVo> language =session.selectList("resume.selectResumeLanguage",resume);  // 언어
		
		multipleVo.setDetailVo(detail);
		multipleVo.setSelfintroVo(selfInfo);
		multipleVo.setCramVo(cram);
		multipleVo.setEducateVo(educate);
		multipleVo.setExperienceVo(experience);
		multipleVo.setLicenceVo(licence);
		multipleVo.setPortfolioVo(portfolio);
		multipleVo.setLanguageVo(language);
		
		return multipleVo;
	}
	
	
	// 이력서 리스트
	public List<ResumeVo> selectResumeList(int id){
		return session.selectList("resume.selectResumeList",id);
	}
}
