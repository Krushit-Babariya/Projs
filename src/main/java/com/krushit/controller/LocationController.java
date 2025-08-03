package com.nj.controller;


import com.nj.entity.Location;
import com.nj.dto.CountryWithRegionDTO;
import com.nj.service.ICountryService;
import com.nj.service.ILocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locations-api")
public class LocationController {
    private ICountryService countriesService;
        
    private ILocationService locationService;

    @Autowired
    public LocationController(ICountryService countriesService, ILocationService locationService) {
        this.countriesService = countriesService;
        this.locationService = locationService;
    }
    
    @PostMapping("/add")
    public String addLocation(@ModelAttribute Location location) {
        locationService.addLocation(location);
        return "redirect:/locations-api/";
    }

    @PostMapping("/delete/{id}")
    public String deleteLocation(@PathVariable("id") int id) {
        locationService.deleteLocation(id);
        return "redirect:/locations/";
    }

    @PostMapping("/update")
    public String updateLocation(@ModelAttribute Location location) {
        locationService.updateLocation(location);
        return "redirect:/locations/";
    }

    @GetMapping("/")
    public String showLocationsPage(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        List<CountryWithRegionDTO> allCountries = countriesService.getAllCountriesWithRegionNames();
        model.addAttribute("countries", countriesService.getAllCountriesWithRegionNames());
        return "locations";
    }
}
