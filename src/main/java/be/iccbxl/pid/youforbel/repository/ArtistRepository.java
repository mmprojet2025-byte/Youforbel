package be.iccbxl.pid.youforbel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.youforbel.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    // Recherche par nom
    List<Artist> findByLastname(String lastname);

    // ⚠️ NE PAS redéclarer findById
    // CrudRepository fournit déjà :
    // Optional<Artist> findById(Long id);
}