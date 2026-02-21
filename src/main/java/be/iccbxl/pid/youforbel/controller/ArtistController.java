package be.iccbxl.pid.youforbel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.service.ArtistService;

/**
 * @Controller
 * Indique que cette classe gère des pages HTML (MVC).
 */
@Controller
public class ArtistController {

    /**
     * Injection du service.
     * Le controller ne parle jamais directement au repository.
     */
    @Autowired
    private ArtistService service;

    /**
     * ===============================
     * READ — Liste des artistes
     * ===============================
     */
    @GetMapping("/artists")
    public String index(Model model) {

        // 1) On récupère les artistes depuis la base
        List<Artist> artists = service.getAllArtists();

        // 2) On envoie les données à la vue
        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");

        // 3) On retourne la page Thymeleaf
        return "artist/index";
    }

    /**
     * ===============================
     * CREATE — Afficher le formulaire
     * ===============================
     */
    @GetMapping("/artists/new")
    public String createForm(Model model) {

        // On crée un objet vide pour le formulaire
        model.addAttribute("artist", new Artist());

        model.addAttribute("title", "Créer un artiste");

        return "artist/create";
    }

    /**
     * ===============================
     * CREATE — Enregistrer en base
     * ===============================
     */
    @PostMapping("/artists")
    public String createSubmit(@ModelAttribute Artist artist) {

        // Spring remplit automatiquement l'objet Artist
        // avec les données du formulaire

        service.addArtist(artist);

        // Redirection vers la liste après enregistrement
        return "redirect:/artists";
    }
}