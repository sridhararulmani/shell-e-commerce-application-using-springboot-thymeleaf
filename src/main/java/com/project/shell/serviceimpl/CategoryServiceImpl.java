package com.project.shell.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shell.entity.Category;
import com.project.shell.repository.CategoryRepository;
import com.project.shell.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAllCategoies() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addNewCategory(Category category) {
		return categoryRepository.save(category);
	}
}
