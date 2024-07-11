package com.project.shell.customvalidationimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.shell.customvalidation.UniqEmail;
import com.project.shell.repository.AccountRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqEmailValidationImpl implements ConstraintValidator<UniqEmail, String> {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext context) {
		if (userEmail == null) {
			return true;
		}
		return !accountRepository.existsByUserEmail(userEmail);
	}
}
