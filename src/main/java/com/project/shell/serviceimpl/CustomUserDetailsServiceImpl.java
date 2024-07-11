package com.project.shell.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.shell.entity.Account;
import com.project.shell.repository.AccountRepository;
import com.project.shell.service.CustomUserDetailsService;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optional = accountRepository.findByUserEmail(username);

		if (optional.isPresent()) {
			Account account = optional.get();
			List<GrantedAuthority> grantedAuthorities = account.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());

			return User.withUsername(account.getUserEmail()).password(account.getUserPassword())
					.authorities(grantedAuthorities).build();
		} else {
			throw new BadCredentialsException("Invalid Email or Password");
		}

	}
}
