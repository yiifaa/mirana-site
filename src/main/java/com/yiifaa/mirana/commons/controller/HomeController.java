package com.yiifaa.mirana.commons.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.exam.domain.RiskOrder;
import com.yiifaa.mirana.exam.helper.ExamStateHelper;
import com.yiifaa.mirana.exam.service.ExamStateService;
import com.yiifaa.mirana.exam.service.RiskOrderService;

@Controller
public class HomeController {
	
	private ExamStateService stateService;
	
	private RiskOrderService riskService;
	
	@Inject
	public HomeController(ExamStateService stateService, RiskOrderService riskService) {
		super();
		this.stateService = stateService;
		this.riskService = riskService;
	}

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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			Object obj = authentication.getPrincipal();
			if(obj instanceof Account) {
				Account account = (Account)obj;
				String id = account.getId();
				List<ExamStateHelper> exams = this.stateService.findByUserId(id);
				List<RiskOrder> orders = this.riskService.findByUserId(id);
				model.addAttribute("exams", exams);
				model.addAttribute("orders", orders);
			}
		}
		return "/client/home";
	}

	@RequestMapping("/sale/home")
	public String saleHome(Model model) {
		List<RiskOrder> orders = this.riskService.findByState(0);
		List<RiskOrder> shenOrder = this.riskService.findByState(10);
		model.addAttribute("orders", orders);
		model.addAttribute("shenOrder", shenOrder);
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
