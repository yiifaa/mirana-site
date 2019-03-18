package com.yiifaa.mirana.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/500")
	public String error500() {
		return "error/500";
	}
	
	@RequestMapping("/404")
	public String error404() {
		return "error/404";
	}
	
	@RequestMapping("/403")
	public String error403() {
		return "error/403";
	}

}
