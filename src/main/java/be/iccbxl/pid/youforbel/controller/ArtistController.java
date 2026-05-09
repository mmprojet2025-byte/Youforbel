package be.iccbxl.pid.youforbel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.service.ArtistService;

import jakarta.validation.Valid;

@Controller
public class ArtistController {

    @Autowired
    private ArtistService service;

    @GetMapping("/artists")
    public String index(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword,

            Model model) {

        Page<Artist> artistsPage =
                service.getArtistsPage(
                        keyword,
                        PageRequest.of(page, size)
                );

        model.addAttribute("artistsPage", artistsPage);
        model.addAttribute("artists", artistsPage.getContent());

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", artistsPage.getTotalPages());

        model.addAttribute("keyword", keyword);

        model.addAttribute("title", "Liste des artistes");

        return "artist/index";
    }

    @GetMapping("/artists/{id}")
    public String show(@PathVariable Long id, Model model) {

        Artist artist = service.getArtistById(id);

        model.addAttribute("artist", artist);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artist/show";
    }

    @GetMapping("/artists/new")
    public String createForm(Model model) {

        model.addAttribute("artist", new Artist());
        model.addAttribute("title", "Créer un artiste");

        return "artist/create";
    }

    @PostMapping("/artists")
    public String createSubmit(@Valid @ModelAttribute Artist artist,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Créer un artiste");
            return "artist/create";
        }

        service.addArtist(artist);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Artiste créé avec succès."
        );

        return "redirect:/artists";
    }

    @GetMapping("/artists/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {

        Artist artist = service.getArtistById(id);

        model.addAttribute("artist", artist);
        model.addAttribute("title", "Modifier un artiste");

        return "artist/create";
    }

    @PostMapping("/artists/{id}")
    public String updateSubmit(@PathVariable Long id,
                               @Valid @ModelAttribute Artist artist,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Modifier un artiste");
            return "artist/create";
        }

        service.updateArtist(id, artist);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Artiste modifié avec succès."
        );

        return "redirect:/artists";
    }

    @PostMapping("/artists/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        service.deleteArtist(id);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Artiste supprimé avec succès."
        );

        return "redirect:/artists";
    }
}