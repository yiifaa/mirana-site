package com.yiifaa.mirana.commons.controller;

import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/client")
public class ClientController {
	
	@RequestMapping("/home")
	public String home() {
		return "client/home";
	}

}
