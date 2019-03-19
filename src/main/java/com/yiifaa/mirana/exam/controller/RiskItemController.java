package com.yiifaa.mirana.exam.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiifaa.mirana.exam.domain.RiskItem;
import com.yiifaa.mirana.exam.query.RiskItemQuery;
import com.yiifaa.mirana.exam.service.RiskItemService;

@Controller
@RequestMapping("/admin/risk")
public class RiskItemController {
	
	private RiskItemService riskItemService;

	@Inject
	public RiskItemController(RiskItemService riskItemService) {
		super();
		this.riskItemService = riskItemService;
	}
	
	@RequestMapping("/list")
	public String list() {
		return "admin/risk/list";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<RiskItem> query(@ModelAttribute RiskItemQuery pageQuery) {
		return this.riskItemService.find(pageQuery);
	}
	

}
