package be.iccbxl.pid.youforbel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.service.ArtistService;

@RestController
public class ApiArtistController {

    private final ArtistService artistService;

    public ApiArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/api/artists")
    public List<Artist> index() {
        return artistService.getAllArtists();
    }

    @GetMapping("/api/artists/{id}")
    public Artist show(@PathVariable Long id) {
        return artistService.getArtistById(id);
    }

    @PostMapping("/api/artists")
    public Artist create(@RequestBody Artist artist) {

        artistService.addArtist(artist);

        return artist;
    }
}
