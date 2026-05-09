package be.iccbxl.pid.youforbel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.youforbel.model.Location;
import be.iccbxl.pid.youforbel.service.LocationService;

@Controller
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public String index(Model model) {

        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("title", "Liste des lieux");

        return "location/index";
    }

    @GetMapping("/locations/{id}")
    public String show(@PathVariable Long id, Model model) {

        Location location = locationService.getLocationById(id);

        model.addAttribute("location", location);
        model.addAttribute("title", "Détail d'un lieu");

        return "location/show";
    }
}