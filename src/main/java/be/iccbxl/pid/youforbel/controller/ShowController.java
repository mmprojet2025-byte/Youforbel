package be.iccbxl.pid.youforbel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.youforbel.model.Show;
import be.iccbxl.pid.youforbel.service.ShowService;

@Controller
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/shows")
    public String index(Model model) {

        model.addAttribute("shows", showService.getAllShows());
        model.addAttribute("title", "Liste des spectacles");

        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(@PathVariable Long id, Model model) {

        Show show = showService.getShowById(id);

        model.addAttribute("show", show);
        model.addAttribute("title", "Détail d'un spectacle");

        return "show/show";
    }
}
