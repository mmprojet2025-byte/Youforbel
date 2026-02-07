package be.iccbxl.pid.youforbel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.youforbel.model.Artist;

// Repository = couche d'accès aux données (SQL)
// CrudRepository fournit déjà : save, findAll, findById, deleteById, etc.
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    // Spring comprend le nom de la méthode et génère la requête :
    // SELECT * FROM artists WHERE lastname = ?
    List<Artist> findByLastname(String lastname);

    // Attention : CrudRepository a déjà findById(...) qui retourne Optional<Artist>.
    // Si tu mets cette méthode, elle retournera directement Artist (ou null si pas trouvé).
    Artist findById(long id);
}