package com.yiifaa.mirana.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiifaa.mirana.commons.domain.Menu;

@Controller
@RequestMapping("/admin")
public class MenuController {
	
	@GetMapping("/menu")
	public String menu(@ModelAttribute Menu menu) {
		menu.setName("MENU");
		return "commons/menu";
	}

}
