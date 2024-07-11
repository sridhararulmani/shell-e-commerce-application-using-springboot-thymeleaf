package com.project.shell.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRegisterDto {

	@DecimalMin(value = "1", inclusive = false, message = "Category Should Not blank")
	@NotNull(message = "Category Must Not be Null")
	private Long categoryId;

	@Size(max = 35, message = "The Product Name Should be with in 15")
	@Size(min = 3, message = "The Product Name Should be more in 3")
	@NotBlank(message = "Product Name Can't be Blank")
	private String productName;

	@DecimalMin(value = "0.0", inclusive = false, message = "Product Price must be greater than or equal to 0")
	private double productPrice;

	@DecimalMin(value = "0.0", inclusive = false, message = "Product Price Discount must be greater than or equal to 0")
	@DecimalMax(value = "100.0", inclusive = false, message = "Product Price Discount must be with in 100")
	private double productDiscount;

	@NotBlank(message = "Product Descrtiption Should not be blank")
	@NotNull(message = "Product Descrtiption Should not be blank")
	@Size(max = 150, message = "The Product Description Should be with in 150 characters")
	@Size(min = 3, message = "The Product Description Should be more then 3 characters")
	private String productDescription;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
