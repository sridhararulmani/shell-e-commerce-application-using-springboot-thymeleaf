package com.project.shell.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shell.dto.CategoryRegisterDto;
import com.project.shell.entity.Category;
import com.project.shell.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("${app.url.prefix}/user/category")
public class CategoryController {

	@Value("${app.url.prefix}")
	private String appUrl;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/addNewCategory")
	public String addNewCategoryPageLoder(@RequestHeader(value = "referer", required = false) String referer,
			CategoryRegisterDto categoryRegisterDto, Model model) {
		model.addAttribute("categoryRegisterDto", categoryRegisterDto);
		model.addAttribute("referer", referer);
		return "addNewCategory";
	}

	@PostMapping("/addNewCategory")
	public String addNewCategory(@Valid @ModelAttribute CategoryRegisterDto categoryRegisterDto,
			BindingResult bindingResult, Category category) {
		if(bindingResult.hasErrors()) {
			return "addNewCategory";
		}
		categoryService.addNewCategory(category);
		return "redirect:" + appUrl + "/user/dashboard";
	}
}
