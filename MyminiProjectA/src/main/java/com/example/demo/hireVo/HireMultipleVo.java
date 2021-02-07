package com.example.demo.hireVo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HireMultipleVo {
	
	private List<HireVo> hireVoList;
	private List<HireInfoVo> hireInfoVoList;
	private List<HireApplyVo> hireApplyVo;

}
