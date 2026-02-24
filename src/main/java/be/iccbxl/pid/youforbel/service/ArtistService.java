package be.iccbxl.pid.youforbel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.repository.ArtistRepository;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // ===============================
    // READ — Tous les artistes
    // ===============================
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }

    // ===============================
    // READ — Un artiste par id
    // ===============================
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artiste introuvable id=" + id));
    }

    // ===============================
    // CREATE
    // ===============================
    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    // ===============================
    // UPDATE
    // ===============================
    public void updateArtist(Long id, Artist artist) {
        artist.setId(id);
        artistRepository.save(artist);
    }

    // ===============================
    // DELETE
    // ===============================
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}