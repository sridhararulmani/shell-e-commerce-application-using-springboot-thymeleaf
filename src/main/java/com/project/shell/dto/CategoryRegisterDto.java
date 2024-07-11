package com.project.shell.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryRegisterDto {

	@NotNull(message = "Category Should Not be Null")
	@Size(max = 15, message = "Category Name Should be with in 15 Characters")
	@Size(min = 3, message = "Category Name Should have atleast 3 Characters")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryRegisterDto [categoryName=" + categoryName + "]";
	}

}
