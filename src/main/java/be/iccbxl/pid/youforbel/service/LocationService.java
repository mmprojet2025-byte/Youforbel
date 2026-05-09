package be.iccbxl.pid.youforbel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.iccbxl.pid.youforbel.model.Location;
import be.iccbxl.pid.youforbel.repository.LocationRepository;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lieu introuvable id=" + id));
    }
}
