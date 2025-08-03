package com.nj.controller;

import com.nj.entity.Country;
import com.nj.entity.Regions;
import com.nj.dto.CountryWithRegionDTO;
import com.nj.service.ICountryService;
import com.nj.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/countries-api")
public class CountryController {

    private ICountryService countryService;

    private IRegionService regionService;

    @Autowired
    public CountryController(ICountryService countryService, IRegionService regionService) {
        this.countryService = countryService;
        this.regionService = regionService;
    }

    @GetMapping("/")
    public String showCountriesPage(Model model) {
        List<CountryWithRegionDTO> countries = countryService.getAllCountriesWithRegionNames();
        List<Regions> regions = regionService.getAllRegions();
        model.addAttribute("countries", countries);
        model.addAttribute("regions", regions);
        return "countries";
    }

    @GetMapping("/add")
    public String showAddCountryForm(Model model) {
        model.addAttribute("country", new Country());
        List<Regions> regions = regionService.getAllRegions();
        model.addAttribute("regions", regions);
        return "add-country";
    }

    @PostMapping
    public String addCountry(@ModelAttribute("country") Country country) {
        countryService.addCountry(country);
        return "redirect:/countries-api/";
    }

    @PostMapping("/countries-by-region")
    public String getCountriesByRegion(@RequestParam("regionId") int regionId, Model model) {
        List<CountryWithRegionDTO> countries = countryService.getCountriesByRegionId(regionId);
        System.out.println("Countries :: " + countries);
        model.addAttribute("countries", countries);
        return "countries-table";
    }
}
