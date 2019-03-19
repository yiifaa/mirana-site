package com.yiifaa.mirana.commons.interceptor;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yiifaa.mirana.commons.domain.Menu;
import com.yiifaa.mirana.commons.service.MenuReaderService;

public class MenuInterceptor extends HandlerInterceptorAdapter {
	
	private MenuReaderService menuService;
	
	private Map<String, List<Menu>> menuCache;

	@Inject
	public MenuInterceptor(MenuReaderService menuService) {
		super();
		this.menuService = menuService;
		if(menuCache == null) {
			menuCache = this.menuService.read();
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			List<Menu> menus = null;
			for (GrantedAuthority name: authentication.getAuthorities()) {
				if (name.getAuthority().equals("ROLE_CLIENT")) {
					menus = menuCache.get("CLIENT");
				}
				if (name.getAuthority().equals("ROLE_SALE")) {
					menus = menuCache.get("SALE");
				}
				if (name.getAuthority().equals("ROLE_ADMIN")) {
					menus = menuCache.get("ADMIN");
				}
			}
			request.setAttribute("menus", menus);
		}
		return super.preHandle(request, response, handler);
	}
	
	
	
	

}
