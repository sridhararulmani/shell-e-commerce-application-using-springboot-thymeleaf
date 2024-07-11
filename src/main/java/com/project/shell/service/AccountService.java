package com.project.shell.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.shell.entity.Account;

@Service
public interface AccountService {

	public Account save(Account account);

	public Optional<Account> findByUserEmail(String userEmail);

	public Optional<Account> findById(Long accountId);

	public void deleteById(Long accountId);

}
