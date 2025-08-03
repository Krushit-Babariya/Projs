package com.nj.controller;

import com.nj.entity.Jobs;
import com.nj.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jobs-api")
public class JobController {
    private IJobService jobService;

    @Autowired
    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String getAllJobs(Model model) {
        List<Jobs> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        model.addAttribute("job", new Jobs());
        return "jobs";
    }

    @GetMapping("/get")
    @ResponseBody
    public Jobs getJobById(@RequestParam("id") int id) {
        return jobService.getJobById(id);
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute("job") Jobs job, RedirectAttributes redirectAttributes) {
        jobService.addJob(job);
        redirectAttributes.addFlashAttribute("message", "Job added successfully.");
        return "redirect:/jobs-api/";
    }

    @PostMapping("/update")
    public String updateJob(@ModelAttribute("job") Jobs job, RedirectAttributes redirectAttributes) {
        jobService.updateJob(job);
        redirectAttributes.addFlashAttribute("message", "Job updated successfully.");
        return "redirect:/jobs-api/";
    }

    @GetMapping("/delete")
    public String deleteJob(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        jobService.deleteJob(id);
        redirectAttributes.addFlashAttribute("message", "Job deleted successfully.");
        return "redirect:/jobs-api/";
    }
}