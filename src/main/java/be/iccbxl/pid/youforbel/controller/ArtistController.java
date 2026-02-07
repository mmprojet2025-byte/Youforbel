package be.iccbxl.pid.youforbel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.service.ArtistService;

@Controller
public class ArtistController {

    @Autowired
    private ArtistService service;

    // ✅ 1) INDEX : liste de tous les artistes
    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = service.getAllArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");
        return "artist/index";
    }

    // ✅ 2) SHOW : fiche d’un artiste (par id)
    @GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") long id) {

        // 1) on récupère l’artiste demandé via le service
        Artist artist = service.getArtist(id);

        // 2) on l’envoie au template
        model.addAttribute("artist", artist);

        // 3) titre de la page
        model.addAttribute("title", "Fiche d'un artiste");

        // 4) template à afficher
        return "artist/show";
    }
}