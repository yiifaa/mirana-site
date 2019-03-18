package com.yiifaa.mirana.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiifaa.mirana.commons.domain.SelectField;

@Controller
@RequestMapping("/form")
public class FieldController {
	
	@RequestMapping("select")
	public String showSelect(@ModelAttribute SelectField sf) {
		sf.setName("test");
		return "formSelect";
	}

}
