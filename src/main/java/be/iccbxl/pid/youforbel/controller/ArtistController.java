package be.iccbxl.pid.youforbel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.service.ArtistService;

import jakarta.validation.Valid;

@Controller
public class ArtistController {

    @Autowired
    private ArtistService service;

    // ===============================
    // READ — Liste
    // ===============================
    @GetMapping("/artists")
    public String index(Model model) {

        List<Artist> artists = service.getAllArtists();

        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");

        return "artist/index";
    }

    // ===============================
    // READ — Fiche
    // ===============================
    @GetMapping("/artists/{id}")
    public String show(@PathVariable Long id, Model model) {

        Artist artist = service.getArtistById(id);

        model.addAttribute("artist", artist);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artist/show";
    }

    // ===============================
    // CREATE — Formulaire
    // ===============================
    @GetMapping("/artists/new")
    public String createForm(Model model) {

        model.addAttribute("artist", new Artist());
        model.addAttribute("title", "Créer un artiste");

        return "artist/create";
    }

    // ===============================
    // CREATE — Save
    // ===============================
    @PostMapping("/artists")
    public String createSubmit(@Valid @ModelAttribute Artist artist,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Créer un artiste");
            return "artist/create";
        }

        service.addArtist(artist);

        return "redirect:/artists";
    }

    // ===============================
    // UPDATE — Formulaire
    // ===============================
    @GetMapping("/artists/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {

        Artist artist = service.getArtistById(id);

        model.addAttribute("artist", artist);
        model.addAttribute("title", "Modifier un artiste");

        return "artist/create";
    }

    // ===============================
    // UPDATE — Save
    // ===============================
    @PostMapping("/artists/{id}")
    public String updateSubmit(@PathVariable Long id,
                               @Valid @ModelAttribute Artist artist,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Modifier un artiste");
            return "artist/create";
        }

        service.updateArtist(id, artist);

        return "redirect:/artists";
    }

    // ===============================
    // DELETE
    // ===============================
    @PostMapping("/artists/{id}/delete")
    public String delete(@PathVariable Long id) {

        service.deleteArtist(id);

        return "redirect:/artists";
    }
}