package be.iccbxl.pid.youforbel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.youforbel.dto.ArtistDTO;
import be.iccbxl.pid.youforbel.dto.ArtistRequestDTO;
import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.service.ArtistService;

import jakarta.validation.Valid;

@RestController
public class ApiArtistController {

    private final ArtistService artistService;

    public ApiArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/api/artists")
    public List<ArtistDTO> index() {

        return artistService.getAllArtists()
                .stream()
                .map(artist -> new ArtistDTO(
                        artist.getId(),
                        artist.getFirstname(),
                        artist.getLastname()
                ))
                .toList();
    }

    @GetMapping("/api/artists/{id}")
    public ArtistDTO show(@PathVariable Long id) {

        Artist artist = artistService.getArtistById(id);

        return new ArtistDTO(
                artist.getId(),
                artist.getFirstname(),
                artist.getLastname()
        );
    }

    @PostMapping("/api/artists")
    public ArtistDTO create(@Valid @RequestBody ArtistRequestDTO request) {

        Artist artist = new Artist();

        artist.setFirstname(request.getFirstname());
        artist.setLastname(request.getLastname());

        artistService.addArtist(artist);

        return new ArtistDTO(
                artist.getId(),
                artist.getFirstname(),
                artist.getLastname()
        );
    }

    @PutMapping("/api/artists/{id}")
    public ArtistDTO update(@PathVariable Long id,
                            @Valid @RequestBody ArtistRequestDTO request) {

        Artist artist = new Artist();

        artist.setFirstname(request.getFirstname());
        artist.setLastname(request.getLastname());

        artistService.updateArtist(id, artist);

        Artist updatedArtist = artistService.getArtistById(id);

        return new ArtistDTO(
                updatedArtist.getId(),
                updatedArtist.getFirstname(),
                updatedArtist.getLastname()
        );
    }

    @DeleteMapping("/api/artists/{id}")
    public void delete(@PathVariable Long id) {

        artistService.deleteArtist(id);
    }
}