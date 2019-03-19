package com.yiifaa.mirana.commons.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String index() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority name: authentication.getAuthorities()) {
			if (name.getAuthority().equals("ROLE_CLIENT")) {
				return "redirect:/app/client/home";
			}
			if (name.getAuthority().equals("ROLE_SALE")) {
				return "redirect:/app/sale/home";
			}
			if (name.getAuthority().equals("ROLE_ADMIN")) {
				return "redirect:/app/admin/home";
			}
		}
		return "index";
	}

	@RequestMapping("/client/home")
	public String clientHome(Model model) {
		return "/client/home";
	}

	@RequestMapping("/sale/home")
	public String saleHome(Model model) {
		return "/sale/home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		return "/admin/home";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
