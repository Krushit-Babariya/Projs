package com.nj.controller;

import com.nj.entity.Regions;
import com.nj.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/regions-api")
public class RegionController {
    private IRegionService regionService;

    @Autowired
    public RegionController(IRegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/")
    public String getAllRegions(Model model) {
        List<Regions> regions = regionService.getAllRegions();
        model.addAttribute("regions", regions);
        return "regions";
    }

    @GetMapping("/{id}")
    public String getRegionById(@PathVariable("id") int id, Model model) {
        Regions region = regionService.getRegionById(id);
        model.addAttribute("region", region);
        return "region-details";
    }

    @PostMapping("/{id}")
    public String deleteRegion(@PathVariable("id") int id) {
        regionService.deleteRegionById(id);
        return "redirect:/regions-api/";
    }

    @PostMapping("/regions")
    public String addRegion(@RequestParam("regionName") String regionName, RedirectAttributes redirectAttributes) {
        try {
            Regions newRegion = new Regions();
            newRegion.setRegionName(regionName);
            regionService.addRegion(newRegion);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("regionError", e.getMessage());
        }
        return "redirect:/regions-api/";
    }
}