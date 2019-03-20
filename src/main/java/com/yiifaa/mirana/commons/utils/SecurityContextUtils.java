package com.yiifaa.mirana.commons.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yiifaa.mirana.commons.domain.Account;

public class SecurityContextUtils {
	
	public final static Account current() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			Object obj = authentication.getPrincipal();
			if(obj instanceof Account) {
				Account account = (Account) obj;
				return account;
			}
		}
		return null;
	}

}
