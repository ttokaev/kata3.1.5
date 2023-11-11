package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/user")
    public String getUser(ModelMap model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user, ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        return "new";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("editUser", userService.getUserById(id));
        return "edit";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delete(user);
        return "redirect:";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:";
    }
}