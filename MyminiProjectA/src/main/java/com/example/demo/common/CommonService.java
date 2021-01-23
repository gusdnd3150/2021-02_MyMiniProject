package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	

	@Autowired
	private CommonDao dao;

}
