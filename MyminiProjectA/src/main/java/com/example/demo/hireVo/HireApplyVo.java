package com.example.demo.hireVo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Component
public class HireApplyVo {
	
	private int hire_id;
	
	private String hire_apply_charge; //채용 담당자
	private String hire_apply_department; //담당자 부서
	private String hire_apply_phone;  // 연락처
	private String hire_apply_time; //접수기간 (마감일)
	private String hire_apply_way; //접수방버 (이력서 / 이력서+ 1분자기소개)
	private String hire_apply_p2p; // 1:1화살면접 여부   Y ,N

}
