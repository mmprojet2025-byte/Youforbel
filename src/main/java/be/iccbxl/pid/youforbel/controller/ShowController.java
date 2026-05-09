package be.iccbxl.pid.youforbel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import be.iccbxl.pid.youforbel.model.Show;
import be.iccbxl.pid.youforbel.service.ArtistService;
import be.iccbxl.pid.youforbel.service.ShowService;

@Controller
public class ShowController {

    private final ShowService showService;
    private final ArtistService artistService;

    public ShowController(ShowService showService,
                          ArtistService artistService) {

        this.showService = showService;
        this.artistService = artistService;
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

    @GetMapping("/shows/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {

        Show show = showService.getShowById(id);

        model.addAttribute("show", show);
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("title", "Modifier un spectacle");

        return "show/edit";
    }

    @PostMapping("/shows/{id}/edit")
    public String updateShow(

            @PathVariable Long id,

            @RequestParam(required = false)
            List<Long> artistIds,

            @RequestParam("posterFile")
            MultipartFile posterFile) {

        showService.updateShowArtists(id, artistIds);

        showService.uploadPoster(id, posterFile);

        return "redirect:/shows/" + id;
    }
}