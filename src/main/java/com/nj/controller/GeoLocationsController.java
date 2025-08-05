package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
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

    public GeoLocationsController(GeoLocationsServiceImpl geoLocationsServiceImpl) {
        this.geoLocationsService = geoLocationsServiceImpl;
    }

    @GetMapping("/")
    public String showLocationPage(Model model) {
        try {
            List<GeoLocation> continents = geoLocationsService.getContinents();
            model.addAttribute("continents", continents);
        } catch (DBException e) {
            model.addAttribute("error", "Database error while fetching continents.");
            e.printStackTrace();
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred while loading continents.");
            e.printStackTrace();
        }
        return "geo-location";
    }

    @PostMapping("/getCountries")
    public String getCountriesByContinent(@RequestParam("continentId") int continentId, Model model) {
        try {
            List<GeoLocation> countries = geoLocationsService.getCountriesByContinentId(continentId);
            model.addAttribute("dataList", countries);
            model.addAttribute("task", "getCountries");
        } catch (DBException e) {
            model.addAttribute("error", "Database error while fetching countries.");
            e.printStackTrace();
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred while loading countries.");
            e.printStackTrace();
        }
        return "dynamic-dropdown";
    }

    @PostMapping("/getStates")
    public String getStatesByCountry(@RequestParam("countryId") int countryId, Model model) {
        try {
            List<GeoLocation> states = geoLocationsService.getStatesByCountryId(countryId);
            model.addAttribute("dataList", states);
            model.addAttribute("task", "getStates");
        } catch (DBException e) {
            model.addAttribute("error", "Database error while fetching states.");
            e.printStackTrace();
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred while loading states.");
            e.printStackTrace();
        }
        return "dynamic-dropdown";
    }

    @PostMapping("/getCities")
    public String getCitiesByState(@RequestParam("stateId") int stateId, Model model) {
        try {
            List<GeoLocation> cities = geoLocationsService.getCitiesByStateId(stateId);
            model.addAttribute("dataList", cities);
            model.addAttribute("task", "getCities");
        } catch (DBException e) {
            model.addAttribute("error", "Database error while fetching cities.");
            e.printStackTrace();
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred while loading cities.");
            e.printStackTrace();
        }
        return "dynamic-dropdown";
    }
}
