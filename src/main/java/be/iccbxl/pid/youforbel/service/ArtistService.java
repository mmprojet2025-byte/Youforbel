package be.iccbxl.pid.youforbel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.repository.ArtistRepository;

// @Service = couche "métier" (business)
// Ici on met la logique liée aux artistes (et on utilise le repository pour la DB)
@Service
public class ArtistService {

    // Injection automatique du repository (Spring crée l'objet pour toi)
    @Autowired
    private ArtistRepository artistRepository;

    // READ: récupérer tous les artistes
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();

        // findAll() vient de CrudRepository
        artistRepository.findAll().forEach(artists::add);

        return artists;
    }

    // READ: récupérer 1 artiste par id
    public Artist getArtist(long id) {
        return artistRepository.findById(id);
    }

    // CREATE: ajouter un artiste
    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    // UPDATE: modifier un artiste
    public void updateArtist(long id, Artist artist) {
        artistRepository.save(artist);
    }

    // DELETE: supprimer un artiste
    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }
}