package com.project.shell.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.shell.entity.Role;
import com.project.shell.service.RoleService;

@Controller
@RequestMapping("${app.url.prefix}/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/* Private only Accessible by Managers Roles Details */
	@PostMapping("/addNewRole")
	@ResponseBody
	public ResponseEntity<Role> addNewRole(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.save(role));
	}

	@GetMapping("/getAllRoles")
	@ResponseBody
	public ResponseEntity<List<Role>> getAllRole() {
		return ResponseEntity.ok(roleService.findAll());
	}
}
