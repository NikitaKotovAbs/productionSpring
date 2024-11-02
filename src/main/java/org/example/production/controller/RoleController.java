package org.example.production.controller;

import org.example.production.model.Role;
import org.example.production.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasRole('Admin')")
@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles/index"; // Путь к вашему шаблону списка ролей
    }

    @GetMapping("/add")
    public String showAddRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "roles/add"; // Путь к вашему шаблону добавления роли
    }

    @PostMapping("/add")
    public String addRole(@Valid @ModelAttribute Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoleForm(@PathVariable Long id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "roles/edit"; // Путь к вашему шаблону редактирования роли
    }

    @GetMapping("/show/{id}")
    public String showRole(@PathVariable Long id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "roles/show"; // Путь к вашему шаблону показа роли
    }


    @PostMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, @Valid @ModelAttribute Role role) {
        role.setId(id);
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/roles";
    }
}
