package com.project.shell.cotroller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.shell.dto.ProductRegisterDto;
import com.project.shell.entity.Account;
import com.project.shell.entity.Category;
import com.project.shell.entity.Product;
import com.project.shell.service.AccountService;
import com.project.shell.service.CategoryService;
import com.project.shell.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("${app.url.prefix}/user/product")
public class ProductController {

	@Value("${app.url.prefix}")
	private String appUrl;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/getAllProduct")
	public String loadGetAllProductPageWithProducts() {
		return "productsMenu";
	}

	@GetMapping("/addNewProduct")
	public String addNewProductPageLoder(@RequestHeader(value = "referer", required = false) String referer,
			ProductRegisterDto productRegisterDto, Model model) {

		List<Category> categories = categoryService.findAllCategoies();

		model.addAttribute("categories", categories);
		model.addAttribute("productRegisterDto", productRegisterDto);
		model.addAttribute("referer", referer);
		return "addNewProduct";
	}

	@PostMapping("/addNewProduct")
	public String addNewProduct(@Valid @ModelAttribute ProductRegisterDto productRegisterDto,
			BindingResult bindingResult, Model model, @RequestParam(value = "productImage") MultipartFile productImage,
			@AuthenticationPrincipal User user) throws IOException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAllCategoies());
			return "addNewProduct";
		}
		byte[] productImageBytes = productImage.getBytes();

		Optional<Account> optional = accountService.findByUserEmail(user.getUsername());
		Account account = optional.get();

		Product newProduct = productService.addNewProduct(productRegisterDto, productImageBytes, account);
		if (newProduct != null) {
			return "redirect:" + appUrl + "/user/dashboard";
		}
		return "redirect:" + appUrl + "/user/product/addNewProduct";
	}

	@GetMapping("/shopDashboard")
	public String shopDashboardPageLoder(@AuthenticationPrincipal User user, Model model) {
		Optional<Account> optional = accountService.findByUserEmail(user.getUsername());
		Account account = optional.get();

		List<Product> products = productService.findAllProducts().stream()
				.filter(product -> !product.getAccount().equals(account)).collect(Collectors.toList());

		model.addAttribute("user", account);
		model.addAttribute("products", products);
		return "shopDashboard";
	}

	@GetMapping("/getProductImage/{productId}")
	public ResponseEntity<byte[]> getProductImageById(@PathVariable(value = "productId") Long productId) {
		Optional<Product> optional = productService.findProductById(productId);
		if (optional.isPresent()) {
			Product product = optional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attactment;filename=\"" + product.getProductName() + "\"")
					.body(product.getProductImage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getMyProducts")
	public String loadMyProductsPage(@AuthenticationPrincipal User user, Model model) {
		Account account = accountService.findByUserEmail(user.getUsername()).get();
		
		model.addAttribute("user", account);
		model.addAttribute("products", account.getProducts());
		return "myProducts";
	}
}
