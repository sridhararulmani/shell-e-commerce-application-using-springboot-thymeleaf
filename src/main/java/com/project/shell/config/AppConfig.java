package com.project.shell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${app.url.prefix}")
	public String appUrl;

	public String getAppUrl() {
		return appUrl;
	}

}
