package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
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

    private static final String GENERIC_ERROR = "Something went wrong. Please try again later..!!";

    public CountryController(ICountryService countryService, IRegionService regionService) {
        this.countryService = countryService;
        this.regionService = regionService;
    }

    @GetMapping("/")
    public String showCountriesPage(Model model) {
        try {
            List<CountryWithRegionDTO> countries = countryService.getAllCountriesWithRegionNames();
            List<Regions> regions = regionService.getAllRegions();
            model.addAttribute("countries", countries);
            model.addAttribute("regions", regions);
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        } catch (ApplicationException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        }
        return "countries";
    }

    @GetMapping("/add")
    public String showAddCountryForm(Model model) {
        try {
            model.addAttribute("country", new Country());
            List<Regions> regions = regionService.getAllRegions();
            model.addAttribute("regions", regions);
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        } catch (ApplicationException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        }
        return "add-country";
    }

    @PostMapping
    public String addCountry(@ModelAttribute("country") Country country, Model model) {
        try {
            countryService.addCountry(country);
            return "redirect:/countries-api/";
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        } catch (ApplicationException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        }
        return "add-country";
    }

    @PostMapping("/countries-by-region")
    public String getCountriesByRegion(@RequestParam("regionId") int regionId, Model model) {
        try {
            List<CountryWithRegionDTO> countries = countryService.getCountriesByRegionId(regionId);
            model.addAttribute("countries", countries);
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        } catch (ApplicationException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", GENERIC_ERROR);
        }
        return "countries-table";
    }
}
