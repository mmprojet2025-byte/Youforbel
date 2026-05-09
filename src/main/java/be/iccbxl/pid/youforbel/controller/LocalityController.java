package be.iccbxl.pid.youforbel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.youforbel.model.Locality;
import be.iccbxl.pid.youforbel.service.LocalityService;

@Controller
public class LocalityController {

    private final LocalityService localityService;

    public LocalityController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @GetMapping("/localities")
    public String index(Model model) {

        model.addAttribute("localities", localityService.getAllLocalities());
        model.addAttribute("title", "Liste des localités");

        return "locality/index";
    }

    @GetMapping("/localities/{id}")
    public String show(@PathVariable Long id, Model model) {

        Locality locality = localityService.getLocalityById(id);

        model.addAttribute("locality", locality);
        model.addAttribute("title", "Détail d'une localité");

        return "locality/show";
    }
}
