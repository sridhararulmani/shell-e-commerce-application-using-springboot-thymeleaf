package com.project.shell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import jakarta.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private ThymeleafViewResolver thymeleafViewResolver;

	@Autowired
	private AppConfig appConfig;

	@PostConstruct
	public void init() {
		thymeleafViewResolver.addStaticVariable("appUrl", appConfig.getAppUrl());
	}
}
