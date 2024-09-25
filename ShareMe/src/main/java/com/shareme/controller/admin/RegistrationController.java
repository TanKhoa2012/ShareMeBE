//package com.shareme.controllers.Admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.shareme.models.Users;
//import com.shareme.repositories.UserRepository;
//
//@RestController
//public class RegistrationController {
//
//	@Autowired
//	private UserRepository userRepo;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@PostMapping("/register/user")
//	public Users registerUsers(@RequestBody Users user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		return userRepo.save(user);
//	}
//}
