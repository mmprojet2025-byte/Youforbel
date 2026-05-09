package be.iccbxl.pid.youforbel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.repository.ArtistRepository;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Page<Artist> getArtistsPage(String keyword, Pageable pageable) {

        if (keyword != null && !keyword.isBlank()) {
            return artistRepository.findByLastnameContainingIgnoreCase(keyword, pageable);
        }

        return artistRepository.findAll(pageable);
    }

    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artiste introuvable id=" + id));
    }

    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public void updateArtist(Long id, Artist artist) {
        artist.setId(id);
        artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}