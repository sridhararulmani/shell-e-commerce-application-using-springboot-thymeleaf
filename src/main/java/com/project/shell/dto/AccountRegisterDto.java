package com.project.shell.dto;

import com.project.shell.customvalidation.UniqEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountRegisterDto {

	@NotBlank(message = "User Name is Required")
	@Size(min = 3, message = "User Name should have atleast 3 characters")
	@Size(max = 15, message = "User Name must be with in 15 characters")
	private String userName;

	@UniqEmail(message = "This Email already have an account")
	@NotBlank(message = "Email is Required")
	@Email(message = "Enter the Valid Email")
	private String userEmail;

	@NotBlank(message = "Password is Required")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid Password.\n"
			+ "1.Password should have ateast 8 alphabatics\n" + "2.Password should have 1 uper Camal case\n"
			+ "3.Password 1 special character 3 numbers.")
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "AccountRegisterDto [userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + "]";
	}

}
