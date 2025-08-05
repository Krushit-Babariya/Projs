package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
import com.nj.entity.Jobs;
import com.nj.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/jobs-api")
public class JobController {
    private IJobService jobService;

    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String getAllJobs(Model model) {
        try {
            List<Jobs> jobs = jobService.getAllJobs();
            model.addAttribute("jobs", jobs);
            model.addAttribute("job", new Jobs());
        } catch (DBException e) {
            model.addAttribute("error", "Database error while fetching jobs.");
            e.printStackTrace();
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred while loading jobs.");
            e.printStackTrace();
        }
        return "jobs";
    }

    @GetMapping("/get")
    @ResponseBody
    public Jobs getJobById(@RequestParam("id") int id) {
        try {
            return jobService.getJobById(id);
        } catch (DBException e) {
            e.printStackTrace();
            return null;
        } catch (ApplicationException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute("job") Jobs job, RedirectAttributes redirectAttributes) {
        try {
            jobService.addJob(job);
            redirectAttributes.addFlashAttribute("message", "Job added successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database error while adding job.");
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Unexpected error occurred while adding job.");
        }
        return "redirect:/jobs-api/";
    }

    @PostMapping("/update")
    public String updateJob(@ModelAttribute("job") Jobs job, RedirectAttributes redirectAttributes) {
        try {
            jobService.updateJob(job);
            redirectAttributes.addFlashAttribute("message", "Job updated successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database error while updating job.");
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Unexpected error occurred while updating job.");
        }
        return "redirect:/jobs-api/";
    }

    @GetMapping("/delete")
    public String deleteJob(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            jobService.deleteJob(id);
            redirectAttributes.addFlashAttribute("message", "Job deleted successfully.");
        } catch (DBException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database error while deleting job.");
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Unexpected error occurred while deleting job.");
        }
        return "redirect:/jobs-api/";
    }
}
