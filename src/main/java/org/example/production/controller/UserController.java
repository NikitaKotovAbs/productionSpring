package org.example.production.controller;

import org.example.production.model.Role;
import org.example.production.model.User;
import org.example.production.service.RoleService;
import org.example.production.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('Admin')")
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, @RequestParam("roleId") Long roleId) {
        Role role = roleService.getRoleById(roleId).orElse(null);
        user.setRole(role);

        // Кодируем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/show";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User userDetails) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDetails.getUsername());
            user.setRole(userDetails.getRole());
            userService.saveUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
