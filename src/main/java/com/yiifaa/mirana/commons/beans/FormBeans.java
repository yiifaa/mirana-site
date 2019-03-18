package com.yiifaa.mirana.commons.beans;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.thymeleaf.spring5.view.ThymeleafView;

@Configuration
public class FormBeans {
	
	@Bean(name="formSelect.html")
	@Scope("prototype")
	public ThymeleafView someViewBean() {
	    ThymeleafView view = new ThymeleafView("form-parts"); // templateName = 'index'
	    view.setStaticVariables(
	            Collections.singletonMap("locale", "zh"));
	    view.setMarkupSelector("select");
	    return view;
	}

}
