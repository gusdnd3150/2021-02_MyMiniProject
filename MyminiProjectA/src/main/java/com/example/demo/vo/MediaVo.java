package com.example.demo.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class MediaVo {
	
	private int oneminute_id; //pk
	private int id;
	private String oneminute_original_name;
	private String oneminute_saved_name;
	private String oneminute_update;

}
