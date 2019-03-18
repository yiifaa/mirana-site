package com.yiifaa.mirana.commons.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiifaa.mirana.commons.domain.Account;
import com.yiifaa.mirana.commons.query.AccountQuery;
import com.yiifaa.mirana.commons.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private AccountService accountService;
	
	@Inject
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@RequestMapping("/list")
	public String list() {
		return "commons/account/list";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<Account> query(@ModelAttribute AccountQuery pageQuery) {
		return this.accountService.find(pageQuery);
	}
	

}
