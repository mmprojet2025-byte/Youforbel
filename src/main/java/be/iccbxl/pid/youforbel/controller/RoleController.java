package be.iccbxl.pid.youforbel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.youforbel.model.Role;
import be.iccbxl.pid.youforbel.service.RoleService;

@Controller
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public String index(Model model) {

        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("title", "Liste des rôles");

        return "role/index";
    }

    @GetMapping("/roles/{id}")
    public String show(@PathVariable Long id, Model model) {

        Role role = roleService.getRoleById(id);

        model.addAttribute("role", role);
        model.addAttribute("title", "Détail d'un rôle");

        return "role/show";
    }
}
