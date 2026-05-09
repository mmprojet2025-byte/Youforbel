package be.iccbxl.pid.youforbel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import be.iccbxl.pid.youforbel.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    // Recherche par nom avec pagination
    Page<Artist> findByLastnameContainingIgnoreCase(
            String lastname,
            Pageable pageable
    );
}