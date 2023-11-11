package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}


	@GetMapping("/user")
	public String showUser(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		model.addAttribute("user", user);
		return "user";
	}

}