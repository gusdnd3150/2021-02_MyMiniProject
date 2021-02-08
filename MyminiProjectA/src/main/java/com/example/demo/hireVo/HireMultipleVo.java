package com.example.demo.hireVo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class HireMultipleVo {
	
	private HireVo hireVo;
	private List<HireInfoVo> hireInfoVoList;
	private HireApplyVo hireApplyVo;

}
