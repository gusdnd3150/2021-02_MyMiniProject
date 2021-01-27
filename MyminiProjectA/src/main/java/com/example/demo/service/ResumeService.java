package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ResumeDao;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao dao;

}
