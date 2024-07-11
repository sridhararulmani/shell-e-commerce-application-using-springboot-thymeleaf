package com.project.shell.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shell.dto.AccountRegisterDto;

@Controller
@RequestMapping("${app.url.prefix}/app/")
public class ApplicationController {

	/* Page Loading Public */
	@GetMapping("/dashboard")
	public String welcomeDashboard() {
		return "welcomeDashboard";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "loginPage";
	}

	@GetMapping("/register/user")
	public String registerUserPage(AccountRegisterDto accountRegisterDto, Model model) {
		model.addAttribute("accountRegisterDto", accountRegisterDto);
		return "registerUser";
	}

	@GetMapping("/register/seller")
	public String registerSellerPage(AccountRegisterDto accountRegisterDto, Model model) {
		model.addAttribute("accountRegisterDto", accountRegisterDto);
		return "registerSeller";
	}
	
}
