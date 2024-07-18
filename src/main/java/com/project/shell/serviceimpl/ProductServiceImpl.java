package com.project.shell.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shell.dto.ProductRegisterDto;
import com.project.shell.entity.Account;
import com.project.shell.entity.Category;
import com.project.shell.entity.Product;
import com.project.shell.repository.CategoryRepository;
import com.project.shell.repository.ProductRepository;
import com.project.shell.service.ProductService;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product addNewProduct(@Valid ProductRegisterDto productRegisterDto, byte[] productImageBytes,
			Account account) {
		Product product = new Product();
		Category category = categoryRepository.findById(productRegisterDto.getCategoryId()).get();

		product.setAccount(account);
		product.setProductName(productRegisterDto.getProductName());
		product.setProductPrice(productRegisterDto.getProductPrice());
		product.setProductDiscount(productRegisterDto.getProductDiscount());
		product.setProductDescription(productRegisterDto.getProductDescription());
		product.setProductRating(4);
		product.setProductImage(productImageBytes);
		product.setCategory(category);

		account.getProducts().add(product);

		return productRepository.save(product);
	}

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findProductById(Long productId) {
		return productRepository.findById(productId);
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProductById(Long productId) {
		productRepository.deleteById(productId);
	}
}
