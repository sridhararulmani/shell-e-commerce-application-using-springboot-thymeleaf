package com.project.shell.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.shell.entity.Category;

@Service
public interface CategoryService {

	public List<Category> findAllCategoies();

	public Category addNewCategory(Category category);

}
