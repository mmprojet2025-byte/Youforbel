package be.iccbxl.pid.youforbel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Representation;
import be.iccbxl.pid.youforbel.repository.RepresentationRepository;

@Service
public class RepresentationService {

    private final RepresentationRepository representationRepository;

    public RepresentationService(RepresentationRepository representationRepository) {
        this.representationRepository = representationRepository;
    }

    public List<Representation> getAllRepresentations() {
        return representationRepository.findAll();
    }

    public Representation getRepresentationById(Long id) {
        return representationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Représentation introuvable id=" + id));
    }
}