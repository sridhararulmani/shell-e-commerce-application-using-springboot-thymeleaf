package com.project.shell.service;

import org.springframework.stereotype.Service;

import com.project.shell.entity.Account;

@Service
public interface DashboardService {

	public boolean isUser(Account account);


}
