package com.example.demo.resumeVo;


import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResumeMultiVo {

	
	private DetailVo detailVo;
	private List<EducateVo> educateVo;
	private List<CramVo>  cramVo;
	private List<ExperienceVo> experienceVo;
	private List<LanguageVo> languageVo;
	private List<LicenceVo> licenceVo;
	private List<PortfolioVo> portfolioVo;
	private SelfintroVo selfintroVo;
	
	
	private String useEducateForm;
	private String useExperienceForm;
	private String uselicenseForm;
	private String useCramForm;
	private String useLanguageForm;
}
