package com.project.shell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${app.url.prefix}")
	private String appUrl;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(registry -> {
			
			registry.requestMatchers(appUrl + "/app/dashboard",
									appUrl + "/user/process_login",
									
									appUrl +"/app/register/user",
									appUrl +"/app/register/seller",
									
									appUrl + "/user/register/user",
									appUrl + "/user/register/seller")
									.permitAll();

			registry.requestMatchers(appUrl + "/user/delete/userAccount",
									appUrl + "/user/delete/sellerAccount",
									
									appUrl + "/user/findByUserEmail",
									
									appUrl + "/user/product/deleteProduct",
									
									appUrl + "/user/manageOthersAccount")
									.hasAnyRole("ADMIN","MANAGER");

			registry.requestMatchers(appUrl + "/roles/**", 
									appUrl + "/app/register/admin",
									appUrl + "/user/register/admin",
									
									appUrl + "/app/register/manager",
									appUrl + "/user/register/manager",
									
									appUrl + "/user/manageOtherUser",
									
									appUrl + "/user/passwordChecked/manageOthersAccount",
									
									appUrl + "/user/delete/adminAccount")
									.hasRole("MANAGER");

			registry.requestMatchers(appUrl + "/user/product/addNewProduct",
									appUrl + "/user/product/updateProduct",
									
									appUrl + "/user/category/addNewCategory",
									appUrl + "/user/category/getAllCategory",
									appUrl + "/user/category/deleteCategory")
									.hasAnyRole("SELLER", "ADMIN", "MANAGER");

			registry.anyRequest().authenticated();
		}).formLogin(loginForm -> {
			loginForm.loginPage(appUrl + "/app/login");
			loginForm.loginProcessingUrl(appUrl + "/user/process_login");
			loginForm.successHandler(customAuthenticationSuccessHandler());
			loginForm.permitAll();
		}).logout(logoutForm -> {
			logoutForm.logoutUrl(appUrl + "/user/passwordChecked/process_logout");
			logoutForm.logoutSuccessUrl(appUrl + "/app/dashboard");
		}).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
}
