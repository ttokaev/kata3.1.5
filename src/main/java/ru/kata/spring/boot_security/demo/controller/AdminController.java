package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private static final String REDIRECT = "redirect:/admin";

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value = "/admin")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("username", principal.getName());
        model.addAttribute("role", userService.getUserByName(principal.getName()).getRoles());
        return "index";
    }

    @GetMapping()
    public String getLoginPage(){
        return REDIRECT;
    }

    @PatchMapping("/user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return REDIRECT;
    }

    @GetMapping("/new")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return REDIRECT;
    }
    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return REDIRECT;
    }

    @DeleteMapping("admin/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.delete(id);
        return REDIRECT;
    }
}
