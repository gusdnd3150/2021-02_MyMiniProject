package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MyPageDao;
import com.example.demo.vo.PagingVo;
import com.example.demo.vo.ResumeVo;

@Service
public class MyPageService {

	@Autowired
	private MyPageDao dao;
	
	public List<ResumeVo> selectResume(PagingVo paging){
		return dao.selectResume(paging);
	}
	
	public int totalUserResume(int id) {
		return dao.totalUserResume(id);
	}
	
	//이력서 상태전환
	public String modResumeState(ResumeVo resume) {
		String result =null;
		int value=0;
		try {
			value=dao.modResumeState(resume);
			if(value > 0) {
				result="success";
			}else {
				result="fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}
	
	//이력서 삭제
	public String deleteResume(int resume_id) {
		String result =null;
		try {
			dao.deleteResume(resume_id);
		    result="success";
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}
}
