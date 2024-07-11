package com.project.shell.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.shell.entity.Role;

@Service
public interface RoleService {

	public Role save(Role role);

	public List<Role> findAll();

	public Role findByRole(String role);

}
