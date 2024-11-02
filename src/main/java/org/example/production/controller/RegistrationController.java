package org.example.production.controller;

import org.example.production.model.Role;
import org.example.production.model.User;
import org.example.production.service.RoleService;
import org.example.production.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        // Шифруем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Назначаем роль "User" при регистрации
        Role role = roleService.findByRoleName("User");
        user.setRole(role);

        userService.saveUser(user); // Сохраняем нового пользователя
        return "redirect:/login";
    }
}
