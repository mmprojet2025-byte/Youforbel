package be.iccbxl.pid.youforbel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.youforbel.model.Representation;
import be.iccbxl.pid.youforbel.service.RepresentationService;

@Controller
public class RepresentationController {

    private final RepresentationService representationService;

    public RepresentationController(RepresentationService representationService) {
        this.representationService = representationService;
    }

    @GetMapping("/representations")
    public String index(Model model) {

        model.addAttribute("representations", representationService.getAllRepresentations());
        model.addAttribute("title", "Liste des représentations");

        return "representation/index";
    }

    @GetMapping("/representations/{id}")
    public String show(@PathVariable Long id, Model model) {

        Representation representation =
                representationService.getRepresentationById(id);

        model.addAttribute("representation", representation);
        model.addAttribute("title", "Détail d'une représentation");

        return "representation/show";
    }
}