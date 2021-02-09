package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class SocketVO {
	
	private String userName;
	private String content;

}
