package com.yiifaa.mirana.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yiifaa.mirana.commons.domain.Menu;

@Controller
public class MenuController {
	
	@GetMapping("/menu")
	public String menu(@ModelAttribute Menu menu) {
		menu.setName("MENU");
		return "commons/menu";
	}

}
