package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
import com.nj.dto.CountryWithRegionDTO;
import com.nj.entity.Location;
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

    public LocationController(ICountryService countriesService, ILocationService locationService) {
        this.countriesService = countriesService;
        this.locationService = locationService;
    }

    @PostMapping("/add")
    public String addLocation(@ModelAttribute Location location, Model model) {
        try {
            locationService.addLocation(location);
            model.addAttribute("message", "Location added successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error while adding location.");
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred while adding location.");
        }
        return "redirect:/locations-api/";
    }

    @PostMapping("/delete/{id}")
    public String deleteLocation(@PathVariable("id") int id, Model model) {
        try {
            locationService.deleteLocation(id);
            model.addAttribute("message", "Location deleted successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error while deleting location.");
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred while deleting location.");
        }
        return "redirect:/locations/";
    }

    @PostMapping("/update")
    public String updateLocation(@ModelAttribute Location location, Model model) {
        try {
            locationService.updateLocation(location);
            model.addAttribute("message", "Location updated successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error while updating location.");
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred while updating location.");
        }
        return "redirect:/locations/";
    }

    @GetMapping("/")
    public String showLocationsPage(Model model) {
        try {
            model.addAttribute("locations", locationService.getAllLocations());
            List<CountryWithRegionDTO> allCountries = countriesService.getAllCountriesWithRegionNames();
            model.addAttribute("countries", allCountries);
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error while loading locations.");
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred while loading locations.");
        }
        return "locations";
    }
}
