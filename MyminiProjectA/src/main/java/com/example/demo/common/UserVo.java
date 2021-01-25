package com.example.demo.common;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class UserVo {
	
	private int id;
	private String userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String joinDate;
	private String autho;  //권한   user,admin

}
