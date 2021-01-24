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
	private String name;
	private String email;
	private String password;
	private String autho;  //권한   user,admin

}
