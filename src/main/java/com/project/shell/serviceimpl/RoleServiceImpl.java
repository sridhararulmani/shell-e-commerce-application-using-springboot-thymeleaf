package com.project.shell.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shell.entity.Role;
import com.project.shell.repository.RoleRepository;
import com.project.shell.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}
}