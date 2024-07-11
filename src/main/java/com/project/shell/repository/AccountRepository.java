package com.project.shell.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.shell.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public Optional<Account> findByUserEmail(String username);

	public boolean existsByUserEmail(String userEmail);

}
