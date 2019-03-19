package com.yiifaa.mirana.exam.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiifaa.mirana.exam.domain.RiskExam;
import com.yiifaa.mirana.exam.query.RiskExamQuery;
import com.yiifaa.mirana.exam.service.RiskExamService;

@Controller
@RequestMapping("/admin/exam")
public class RiskExampleController {
	
	private RiskExamService riskService;
	
	@Inject
	public RiskExampleController(RiskExamService riskService) {
		super();
		this.riskService = riskService;
	}

	@RequestMapping("/list")
	public String list() {
		return "admin/exam/list";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<RiskExam> query(@ModelAttribute RiskExamQuery pageQuery) {
		return this.riskService.find(pageQuery);
	}
	
}
