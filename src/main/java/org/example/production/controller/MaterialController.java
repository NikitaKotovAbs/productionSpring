package org.example.production.controller;

import org.example.production.model.Material;
import org.example.production.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasRole('User') or hasRole('Builder')")
@Controller
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public String listMaterials(Model model) {
        model.addAttribute("materials", materialService.findAll());
        return "materials/index";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/add")
    public String showAddMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        return "materials/add";
    }

    @PostMapping("/add")
    public String addMaterial(@Valid @ModelAttribute Material material) {
        materialService.save(material);
        return "redirect:/materials";
    }

    @GetMapping("/edit/{id}")
    public String showEditMaterialForm(@PathVariable Long id, Model model) {
        Material material = materialService.findById(id);
        model.addAttribute("material", material);
        return "materials/edit";
    }

    @PostMapping("/edit/{id}")
    public String editMaterial(@PathVariable Long id, @Valid @ModelAttribute Material material) {
        material.setId(id);
        materialService.save(material);
        return "redirect:/materials";
    }

    @GetMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        materialService.deleteById(id);
        return "redirect:/materials";
    }

    @GetMapping("/show/{id}")
    public String showMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.findById(id);
        model.addAttribute("material", material);
        return "materials/show";
    }
}
