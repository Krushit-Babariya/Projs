package com.nj.controller;

import com.nj.entity.GeoLocation;
import com.nj.service.IGeoLocationsService;
import com.nj.service.impl.GeoLocationsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/geo-locations-api")
public class GeoLocationsController {

    private IGeoLocationsService geoLocationsService;

    @Autowired
    public GeoLocationsController(GeoLocationsServiceImpl geoLocationsServiceImpl) {
        this.geoLocationsService = geoLocationsServiceImpl;
    }

    @GetMapping("/")
    public String showLocationPage(Model model) {
        List<GeoLocation> continents = geoLocationsService.getContinents();
        model.addAttribute("continents", continents);
        return "geo-location";
    }

    @PostMapping("/getCountries")
    public String getCountriesByContinent(@RequestParam("continentId") int continentId, Model model) {
        List<GeoLocation> countries = geoLocationsService.getCountriesByContinentId(continentId);
        model.addAttribute("dataList", countries);
        model.addAttribute("task", "getCountries");
        return "dynamic-dropdown";
    }

    @PostMapping("/getStates")
    public String getStatesByCountry(@RequestParam("countryId") int countryId, Model model) {
        List<GeoLocation> states = geoLocationsService.getStatesByCountryId(countryId);
        model.addAttribute("dataList", states);
        model.addAttribute("task", "getStates");
        return "dynamic-dropdown";
    }

    @PostMapping("/getCities")
    public String getCitiesByState(@RequestParam("stateId") int stateId, Model model) {
        List<GeoLocation> cities = geoLocationsService.getCitiesByStateId(stateId);
        model.addAttribute("dataList", cities);
        model.addAttribute("task", "getCities");
        return "dynamic-dropdown";
    }
}
