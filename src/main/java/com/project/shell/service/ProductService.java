package com.project.shell.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.shell.dto.ProductRegisterDto;
import com.project.shell.entity.Account;
import com.project.shell.entity.Product;

import jakarta.validation.Valid;

@Service
public interface ProductService {

	public Product addNewProduct(@Valid ProductRegisterDto productRegisterDto, byte[] productImageBytes, Account account);

	public List<Product> findAllProducts();

	public Optional<Product> findProductById(Long productId);

	public void updateProduct(Product product);

	public void deleteProductById(Long productId);

}
