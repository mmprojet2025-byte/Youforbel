package be.iccbxl.pid.youforbel.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import be.iccbxl.pid.youforbel.model.Artist;
import be.iccbxl.pid.youforbel.model.Show;
import be.iccbxl.pid.youforbel.repository.ArtistRepository;
import be.iccbxl.pid.youforbel.repository.ShowRepository;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ArtistRepository artistRepository;

    public ShowService(ShowRepository showRepository,
                       ArtistRepository artistRepository) {

        this.showRepository = showRepository;
        this.artistRepository = artistRepository;
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Spectacle introuvable id=" + id));
    }

    public void updateShowArtists(Long showId,
                                  List<Long> artistIds) {

        Show show = getShowById(showId);

        List<Artist> artists = new ArrayList<>();

        if (artistIds != null) {
            artistRepository.findAllById(artistIds)
                    .forEach(artists::add);
        }

        show.setArtists(artists);

        showRepository.save(show);
    }

    public void uploadPoster(Long showId, MultipartFile posterFile) {

        if (posterFile == null || posterFile.isEmpty()) {
            return;
        }

        try {
            Show show = getShowById(showId);

            String originalFilename = posterFile.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String filename = UUID.randomUUID().toString() + extension;

            Path uploadPath = Path.of("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(filename);

            Files.copy(posterFile.getInputStream(), filePath);

            show.setPosterUrl("/uploads/" + filename);

            showRepository.save(show);

        } catch (IOException e) {
            throw new RuntimeException("Erreur pendant l'upload de l'image", e);
        }
    }
}