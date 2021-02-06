package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class MediaVo {
	
	private int media_id; //pk
	private int id;
	private String media_original;
	private String media_saved;
	private String media_update;

}
