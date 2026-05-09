package be.iccbxl.pid.youforbel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Locality;
import be.iccbxl.pid.youforbel.repository.LocalityRepository;

@Service
public class LocalityService {

    private final LocalityRepository localityRepository;

    public LocalityService(LocalityRepository localityRepository) {
        this.localityRepository = localityRepository;
    }

    public List<Locality> getAllLocalities() {
        return localityRepository.findAll();
    }

    public Locality getLocalityById(Long id) {
        return localityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Localité introuvable id=" + id));
    }
}
