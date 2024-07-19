package com.project.shell.cotroller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.project.shell.component.ProfileImageGenerator;
import com.project.shell.dto.AccountRegisterDto;
import com.project.shell.dto.UpdateAccountInfoDto;
import com.project.shell.entity.Account;
import com.project.shell.entity.Role;
import com.project.shell.repository.RoleRepository;
import com.project.shell.service.AccountService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("${app.url.prefix}/user")
public class AccountController {

	@Value("${app.url.prefix}")
	private String appUrl;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ProfileImageGenerator profileImageGenerator;

	/* Registering Accounts */
	@PostMapping("/register/user")
	public String registerUser(@Valid @ModelAttribute AccountRegisterDto accountRegisterDto,
			BindingResult bindingResult, @RequestParam(value = "userProfile") MultipartFile multipartFile,
			@RequestParam(value = "passwordConfirm") String passwordConfirm, Model model) throws IOException {
		if (!passwordConfirm.equals(accountRegisterDto.getUserPassword())) {
			model.addAttribute("passwordMissMatch", "Your Confirmed Password and User Password Not Matched");
			return "registerUser";
		}
		if (bindingResult.hasErrors()) {
			return "registerUser";
		}

		Account account = new Account();
		account.getRoles().add(roleRepository.findByRole("ROLE_USER"));

		account.setUserName(accountRegisterDto.getUserName());
		account.setUserEmail(accountRegisterDto.getUserEmail());
		account.setUserPassword(passwordEncoder.encode(accountRegisterDto.getUserPassword()));
		if (!multipartFile.isEmpty()) {
			account.setUserProfile(multipartFile.getBytes());
		} else {
			System.out.println("Profile Generating...");
			byte[] profileImage = profileImageGenerator.generateProfileImage(accountRegisterDto.getUserName());
			account.setUserProfile(profileImage);
			System.out.println(profileImage);
		}
		accountService.save(account);
		return "redirect:" + appUrl + "/user/dashboard";

	}

	@PostMapping("/register/seller")
	public String registerSeller(@Valid @ModelAttribute AccountRegisterDto accountRegisterDto,
			BindingResult bindingResult, @RequestParam(value = "userProfile") MultipartFile multipartFile,
			@RequestParam(value = "passwordConfirm") String passwordConfirm, Model model) throws IOException {
		if (!passwordConfirm.equals(accountRegisterDto.getUserPassword())) {
			model.addAttribute("passwordMissMatch", "Your Confirmed Password and User Password Not Matched");
			return "registerSeller";
		}
		if (bindingResult.hasErrors()) {
			return "registerSeller";
		}

		Account account = new Account();

		account.getRoles().add(roleRepository.findByRole("ROLE_USER"));
		account.getRoles().add(roleRepository.findByRole("ROLE_SELLER"));

		account.setUserName(accountRegisterDto.getUserName());
		account.setUserEmail(accountRegisterDto.getUserEmail());
		account.setUserPassword(passwordEncoder.encode(accountRegisterDto.getUserPassword()));
		if (!multipartFile.isEmpty()) {
			account.setUserProfile(multipartFile.getBytes());
		} else {
			System.out.println("Profile Generating...");
			byte[] profileImage = profileImageGenerator.generateProfileImage(accountRegisterDto.getUserName());
			account.setUserProfile(profileImage);
			System.out.println(profileImage);
		}
		accountService.save(account);
		return "redirect:" + appUrl + "/user/dashboard";

	}

	@PostMapping("/register/admin")
	public String registerAdmin(@Valid @ModelAttribute AccountRegisterDto accountRegisterDto,
			BindingResult bindingResult, @RequestParam(value = "userProfile") MultipartFile multipartFile,
			@RequestParam(value = "passwordConfirm") String passwordConfirm, Model model) throws IOException {
		if (!passwordConfirm.equals(accountRegisterDto.getUserPassword())) {
			model.addAttribute("passwordMissMatch", "Your Confirmed Password and User Password Not Matched");
			return "registerUser";
		}
		if (bindingResult.hasErrors()) {
			return "registerAdmin";
		}

		Account account = new Account();

		account.getRoles().add(roleRepository.findByRole("ROLE_ADMIN"));
		account.getRoles().add(roleRepository.findByRole("ROLE_SELLER"));

		account.setUserName(accountRegisterDto.getUserName());
		account.setUserEmail(accountRegisterDto.getUserEmail());
		account.setUserPassword(passwordEncoder.encode(accountRegisterDto.getUserPassword()));
		if (!multipartFile.isEmpty()) {
			account.setUserProfile(multipartFile.getBytes());
		} else {
			System.out.println("Profile Generating...");
			byte[] profileImage = profileImageGenerator.generateProfileImage(accountRegisterDto.getUserName());
			account.setUserProfile(profileImage);
			System.out.println(profileImage);
		}
		accountService.save(account);
		return "redirect:" + appUrl + "/user/dashboard";

	}

	@PostMapping("/register/manager")
	public String registerManager(@Valid @ModelAttribute AccountRegisterDto accountRegisterDto,
			BindingResult bindingResult, @RequestParam(value = "userProfile") MultipartFile multipartFile,
			@RequestParam(value = "passwordConfirm") String passwordConfirm, Model model) throws IOException {
		if (!passwordConfirm.equals(accountRegisterDto.getUserPassword())) {
			model.addAttribute("passwordMissMatch", "Your Confirmed Password and User Password Not Matched");
			return "registerUser";
		}

		if (bindingResult.hasErrors()) {
			return "registerManager";
		}

		Account account = new Account();

		account.getRoles().add(roleRepository.findByRole("ROLE_USER"));
		account.getRoles().add(roleRepository.findByRole("ROLE_MANAGER"));
		account.getRoles().add(roleRepository.findByRole("ROLE_ADMIN"));
		account.getRoles().add(roleRepository.findByRole("ROLE_SELLER"));

		account.setUserName(accountRegisterDto.getUserName());
		account.setUserEmail(accountRegisterDto.getUserEmail());
		account.setUserPassword(passwordEncoder.encode(accountRegisterDto.getUserPassword()));
		if (!multipartFile.isEmpty()) {
			account.setUserProfile(multipartFile.getBytes());
		} else {
			System.out.println("Profile Generating...");
			byte[] profileImage = profileImageGenerator.generateProfileImage(accountRegisterDto.getUserName());
			account.setUserProfile(profileImage);
			System.out.println(profileImage);
		}
		accountService.save(account);
		return "redirect:" + appUrl + "/user/dashboard";

	}

	/* Fetching UserProfile */
	@GetMapping("/getProfilePicture/{accountId}")
	public ResponseEntity<byte[]> getProfilePicture(@PathVariable(value = "accountId") Long accountId) {
		Optional<Account> account = accountService.findById(accountId);
		if (account.isPresent()) {
			Account user = account.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + user.getUserName() + "\"")
					.body(user.getUserProfile());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/findByUserEmail")
	public String findByUserEmail(@AuthenticationPrincipal User user,
			@RequestParam(value = "userEmail") String userEmail, Model model) {
		Optional<Account> optional = accountService.findByUserEmail(userEmail);
		Optional<Account> userOptional = accountService.findByUserEmail(user.getUsername());
		Account currentUser = userOptional.get();
		if (optional.isPresent()) {
			Account account = optional.get();
			model.addAttribute("user", currentUser);
			if (userEmail.equals(currentUser.getUserEmail())) {
				model.addAttribute("userNotFounded",
						"You Can't Pass Your Own Account to Modify here. If you Want Go Back to Home check Manage My Caaount Option");
				return "manageOthersAccount";
			}
			model.addAttribute("findedAccount", account);
			return "manageOthersAccount";
		} else {
			model.addAttribute("user", currentUser);
			model.addAttribute("userNotFounded", "No User Present in Your Entered Email");
			return "manageOthersAccount";
		}
	}

	@PostMapping("/manageOthersAccount/{accountId}")
	public String manageUserById(@PathVariable(value = "accountId") Long accountId, Model model) {
		Optional<Account> optional = accountService.findById(accountId);
		Account account = optional.get();
		model.addAttribute("account", account);
		return "manageOthersAccount";
	}

	/* Page Loaders */
	@GetMapping("/register/admin")
	public String registerPage(AccountRegisterDto accountRegisterDto, Model model,
			@RequestHeader(value = "referer", required = false) String referer) {
		model.addAttribute("referer", referer);
		model.addAttribute("accountRegisterDto", accountRegisterDto);
		return "registerAdmin";
	}

	@GetMapping("/register/manager")
	public String registerManagerPage(AccountRegisterDto accountRegisterDto, Model model,
			@RequestHeader(value = "referer", required = false) String referer) {
		model.addAttribute("referer", referer);
		model.addAttribute("accountRegisterDto", accountRegisterDto);
		return "registerManager";
	}

	@GetMapping("/getUserDetails")
	public String loadUserDetailsPage(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", accountService.findByUserEmail(user.getUsername()).get());
		return "userDetails";
	}

	@GetMapping("/dashboard")
	public String loadUserDashboar(@AuthenticationPrincipal User user, Model model) {
		Optional<Account> optional = accountService.findByUserEmail(user.getUsername());
		if (optional.isPresent()) {
			Account account = optional.get();
			model.addAttribute("user", account);
			Set<Role> currentUserRoles = account.getRoles();
			Role userRoleOptional = roleRepository.findByRole("ROLE_USER");
			Role managerRoleOptional = roleRepository.findByRole("ROLE_MANAGER");
			Role adminRoleOptional = roleRepository.findByRole("ROLE_ADMIN");
			Role sellerRoleOptional = roleRepository.findByRole("ROLE_SELLER");

			List<Account> accounts = accountService.findAllAccountsFromShell();
			int totalShellAccountsCount = accounts.size();

			int userAccountCount = (int) accounts.stream().filter(a -> a.getRoles().contains(userRoleOptional)).count();
			int adminAccountCount = (int) accounts.stream().filter(a -> a.getRoles().contains(adminRoleOptional)).count();
			int sellerAccountCount = (int) accounts.stream().filter(a -> a.getRoles().contains(sellerRoleOptional)).count();
			int managerAccountCount = (int) accounts.stream().filter(a -> a.getRoles().contains(managerRoleOptional)).count();
			
			
			if (currentUserRoles.contains(userRoleOptional)) {
				System.out.println("isUser");
				model.addAttribute("isUser", true);
			}
			if (currentUserRoles.contains(sellerRoleOptional)) {
				System.out.println("isSeller");
				model.addAttribute("isSeller", true);
			}
			if (currentUserRoles.contains(adminRoleOptional)) {
				System.out.println("isAdmin");
				model.addAttribute("isAdmin", true);
			}
			if (currentUserRoles.contains(managerRoleOptional)) {
				System.out.println("isManager");
				model.addAttribute("isManager", true);
			}
			if (currentUserRoles.contains(managerRoleOptional) || currentUserRoles.contains(adminRoleOptional)) {
				model.addAttribute("totalAccountCount", totalShellAccountsCount);
				model.addAttribute("userCount", userAccountCount);
				model.addAttribute("sellerCount", sellerAccountCount);
				model.addAttribute("adminCount", adminAccountCount);
				model.addAttribute("managerCount", managerAccountCount);
			}
			return "userDashboard";
		}
		return "redirect:" + appUrl + "/app/login";

	}

	@GetMapping("/manageAccount")
	public String loadManageAccountPage(@AuthenticationPrincipal User user, Model model) {
		Optional<Account> optional = accountService.findByUserEmail(user.getUsername());
		Role userRoleOptional = roleRepository.findByRole("ROLE_USER");
		Role managerRoleOptional = roleRepository.findByRole("ROLE_MANAGER");
		Role adminRoleOptional = roleRepository.findByRole("ROLE_ADMIN");
		Role sellerRoleOptional = roleRepository.findByRole("ROLE_SELLER");
		if (optional.isPresent()) {
			Account account = optional.get();
			if (!account.getRoles().contains(managerRoleOptional) && account.getRoles().contains(adminRoleOptional)) {
				model.addAttribute("isAdmin", true);
			}
			if ((account.getRoles().contains(userRoleOptional) && account.getRoles().size() == 1)
					|| (account.getRoles().contains(sellerRoleOptional) && account.getRoles().size() == 1)) {
				model.addAttribute("isAdmin", false);
			}
			model.addAttribute("user", account);
		}
		return "manageAccount";
	}

	@GetMapping("/passwordConfirm")
	public String loadPasswordConfitmPage(@RequestHeader(value = "referer", required = false) String referer,
			Model model, @RequestParam(value = "path") String path) {
		model.addAttribute("referer", referer);
		model.addAttribute("path", path);
		return "passwordConfirm";
	}

	@GetMapping("/passwordChecked/manageOthersAccount")
	public String manageUserPageLoder(@AuthenticationPrincipal User user, Model model) {
		Optional<Account> optional = accountService.findByUserEmail(user.getUsername());
		if (optional.isPresent()) {
			Account account = optional.get();
			model.addAttribute("user", account);
		}
		return "manageOthersAccount";
	}

	@GetMapping("/updateAccountInfo")
	public String updateAccountInfoPageLoder(@AuthenticationPrincipal User user, Model model,
			@RequestHeader(value = "referer", required = false) String referer,
			UpdateAccountInfoDto updateAccountInfoDto) {
		Account account = accountService.findByUserEmail(user.getUsername()).get();
		model.addAttribute("user", account);
		model.addAttribute("referer", referer);
		updateAccountInfoDto.setUserName(account.getUserName());
		model.addAttribute("updateAccountInfoDto", updateAccountInfoDto);
		return "updateAccountInfo";
	}

	@GetMapping("/passwordChecked/deleteMyAccount")
	public String loadConfirmDetailsPageForDeleteMyAccount(@AuthenticationPrincipal User user, Model model,
			@RequestHeader(value = "referer", required = false) String referer) {
		Account account = accountService.findByUserEmail(user.getUsername()).get();

		model.addAttribute("user", account);
		model.addAttribute("referer", referer);
		model.addAttribute("accountId", account.getAccountId());
		model.addAttribute("message",
				"Are you Sure! You want to Delete Your Account Permenently from Shell Application.");
		return "confirmMessage";
	}

	@GetMapping("/deleteFindedAccount/{accountId}")
	public String loadConfirmDetailsPageForDeleteFindedAccount(@AuthenticationPrincipal User user, Model model,
			@RequestHeader(value = "referer", required = false) String referer,
			@PathVariable(value = "accountId") Long findedAccountId) {
		Account account = accountService.findByUserEmail(user.getUsername()).get();

		Optional<Account> byId = accountService.findById(findedAccountId);
		Account accountFindedById = byId.get();

		model.addAttribute("user", account);
		model.addAttribute("referer", referer);
		model.addAttribute("accountId", accountFindedById.getAccountId());
		model.addAttribute("message",
				"Are you Sure! You want to Delete " + accountFindedById.getUserName() + " Account having Email Id with "
						+ accountFindedById.getUserEmail() + " Permenently from Shell Application.");
		return "confirmMessage";
	}

	@GetMapping("/deleteProfilleImageById/{accountId}")
	public String deleteProfileImageById(@RequestHeader(value = "referer", required = false) String referer,
			@PathVariable(value = "accountId") Long accountId) {
		Account account = accountService.findById(accountId).get();
		account.setUserProfile(null);
		accountService.save(account);
		return "redirect:" + referer;
	}

	@PostMapping("/updateAccountInfo")
	public String updateAccountInfo(@Valid @ModelAttribute UpdateAccountInfoDto updateAccountInfoDto,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal User user,
			@RequestHeader(value = "referer", required = false) String referer,
			@RequestParam(value = "userProfile") MultipartFile userProfile) throws IOException {
		Account account = accountService.findByUserEmail(user.getUsername()).get();

		System.out.println("Updating Account");

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", account);
			return "updateAccountInfo";
		}
		if (account.getUserProfile() == null) {
			System.out.println("Profile Generating...");
			byte[] profileImage = profileImageGenerator.generateProfileImage(updateAccountInfoDto.getUserName());
			account.setUserProfile(profileImage);
			System.out.println(profileImage);
		}
		if (!userProfile.isEmpty()) {
			account.setUserProfile(userProfile.getBytes());
		}
		if (!updateAccountInfoDto.getUserName().isEmpty() && updateAccountInfoDto.getUserName() != null) {
			System.out.println("Updating Account User Name");
			account.setUserName(updateAccountInfoDto.getUserName());
			accountService.save(account);
			return "redirect:" + appUrl + "/user/getUserDetails";
		}
		if ((updateAccountInfoDto.getUserName() != null
				&& !account.getUserName().equals(updateAccountInfoDto.getUserName()))
				|| (!userProfile.isEmpty() && userProfile != null)) {
			System.out.println("Updating Account Details Name or Profile");
			accountService.save(account);
			return "redirect:" + appUrl + "/user/getUserDetails";
		} else {
			return "redirect:" + appUrl + "/user/updateAccountInfo";
		}
	}

	/* Password Checking Logic */
	@PostMapping("/checkPassword/{path}")
	public String checkEnteredPasswordIsMatched(@AuthenticationPrincipal User user, Model model,
			@PathVariable(value = "path") String path, @RequestParam(value = "password") String password) {
		Optional<Account> optional = accountService.findByUserEmail(user.getUsername());
		Account account = optional.get();
		if (passwordEncoder.matches(password, account.getUserPassword())) {
			return "redirect:" + appUrl + "/user/passwordChecked/" + path;
		} else {
			model.addAttribute("error", "Invalid Password");
			model.addAttribute("path", path);
			return "passwordConfirm";
		}
	}

	/* Delete Account Logic */
	@GetMapping("/deleteAccountInfoByAccountId/{accountId}")
	public String deleteAccountInfoByAccountId(@AuthenticationPrincipal User user,
			@PathVariable(value = "accountId") Long accountId, Model model) {
		Optional<Account> optional = accountService.findById(accountId);
		Account account = optional.get();
		Optional<Account> currentUserOptional = accountService.findByUserEmail(user.getUsername());
		Account currentUser = currentUserOptional.get();

		if (accountId == currentUser.getAccountId()) {
			deleteAccountById(accountId, account);
			return "redirect:" + appUrl + "/user/passwordChecked/process_logout";
		}
		deleteAccountById(accountId, account);
		model.addAttribute("user", currentUser);
		return "redirect:" + appUrl + "/user/dashboard";
	}

	public void deleteAccountById(Long accountId, Account account) {
		accountService.deleteById(account.getAccountId());
	}
}
