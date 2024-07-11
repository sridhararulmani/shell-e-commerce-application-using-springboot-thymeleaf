package com.project.shell.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Value("${app.url.prefix}")
	private String appUrl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (authentication.isAuthenticated()) {
			response.sendRedirect(appUrl + "/user/dashboard");
		} else {
			response.sendRedirect(appUrl + "/app/login");
		}
	}
}
