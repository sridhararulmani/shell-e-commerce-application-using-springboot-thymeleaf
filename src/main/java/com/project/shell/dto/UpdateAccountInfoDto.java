package com.project.shell.dto;

import jakarta.validation.constraints.Size;

public class UpdateAccountInfoDto {

	@Size(max = 15, message = "User Name must be with in 15 characters")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
