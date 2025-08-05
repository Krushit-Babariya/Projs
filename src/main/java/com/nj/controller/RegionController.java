package com.nj.controller;

import com.nj.common.annotations.SuperAdminOnly;
import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
import com.nj.entity.Regions;
import com.nj.service.IRegionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SuperAdminOnly
@RequestMapping("/regions-api")
public class RegionController {
    private IRegionService regionService;

    public RegionController(IRegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/")
    public String getAllRegions(Model model) {
        try {
            List<Regions> regions = regionService.getAllRegions();
            model.addAttribute("regions", regions);
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error while fetching regions.");
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error while loading regions.");
        }
        return "regions";
    }

    @GetMapping("/{id}")
    public String getRegionById(@PathVariable("id") int id, Model model) {
        try {
            Regions region = regionService.getRegionById(id);
            model.addAttribute("region", region);
        } catch (DBException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database error while fetching region.");
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error while loading region.");
        }
        return "region-details";
    }

    @PostMapping("/{id}")
    public String deleteRegion(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            regionService.deleteRegionById(id);
            redirectAttributes.addFlashAttribute("message", "Region deleted successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database error while deleting region.");
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Unexpected error occurred.");
        }
        return "redirect:/regions-api/";
    }

    @PostMapping("/regions")
    public String addRegion(@RequestParam("regionName") String regionName, RedirectAttributes redirectAttributes) {
        try {
            Regions newRegion = new Regions();
            newRegion.setRegionName(regionName);
            regionService.addRegion(newRegion);
            redirectAttributes.addFlashAttribute("message", "Region added successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("regionError", "Database error while adding region.");
        } catch (ApplicationException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("regionError", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("regionError", "Unexpected error while adding region.");
        }
        return "redirect:/regions-api/";
    }

    @GetMapping("/change-status")
    public String changeStatus(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            List<String> warnings = regionService.getAffectedLocations(id);
            if (warnings.isEmpty()) {
                regionService.changeStatus(id);
                redirectAttributes.addFlashAttribute("message", "Region status changed successfully.");
            } else {
                redirectAttributes.addFlashAttribute("warnings", warnings);
            }
        } catch (DBException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database error while changing status.");
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Unexpected error while changing status.");
        }
        return "redirect:/regions-api/";
    }
}
