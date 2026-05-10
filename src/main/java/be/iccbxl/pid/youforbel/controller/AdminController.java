package be.iccbxl.pid.youforbel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String index(Model model) {

        model.addAttribute("title", "Espace administrateur");

        return "admin/index";
    }
}