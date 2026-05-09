package be.iccbxl.pid.youforbel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
}