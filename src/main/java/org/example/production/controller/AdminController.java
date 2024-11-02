package org.example.production.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/index")
    @PreAuthorize("hasRole('Admin')")
    public String adminDashboard() {
        return "admin/index";
    }

    @GetMapping("/user/index")
    @PreAuthorize("hasAnyRole('User')")
    public String userDashboard() {
        return "user/index";
    }
}
