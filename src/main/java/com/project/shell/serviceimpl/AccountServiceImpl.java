package com.project.shell.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shell.entity.Account;
import com.project.shell.repository.AccountRepository;
import com.project.shell.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> findByUserEmail(String userEmail) {
		return accountRepository.findByUserEmail(userEmail);
	}

	@Override
	public Optional<Account> findById(Long accountId) {
		return accountRepository.findById(accountId);
	}

	@Override
	public void deleteById(Long accountId) {
		accountRepository.deleteById(accountId);
	}
	
	@Override
	public List<Account> findAllAccountsFromShell() {
		return accountRepository.findAll();
	}
}
