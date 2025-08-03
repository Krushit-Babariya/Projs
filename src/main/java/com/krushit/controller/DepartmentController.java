package com.nj.controller;

import com.nj.common.annotations.AdminOnly;
import com.nj.entity.Department;
import com.nj.entity.Employees;
import com.nj.entity.Location;
import com.nj.entity.Regions;
import com.nj.dto.DepartmentDetailsDTO;
import com.nj.dto.LocationWithCountryRegionDTO;
import com.nj.service.IDepartmentService;
import com.nj.service.IEmployeeService;
import com.nj.service.ILocationService;
import com.nj.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/departments-api")
@AdminOnly
public class DepartmentController {
    private IEmployeeService employeeService;

    private ILocationService locationService;

    private IDepartmentService departmentService;

    private IRegionService regionService;

    @Autowired
    public DepartmentController(IEmployeeService employeeService, ILocationService locationService, IDepartmentService departmentService, IRegionService regionService) {
        this.employeeService = employeeService;
        this.locationService = locationService;
        this.departmentService = departmentService;
        this.regionService = regionService;
    }

    @GetMapping("/")
    @AdminOnly
    public String getAllDepartments(Model model) {
        List<DepartmentDetailsDTO> departments = departmentService.getAllDepartmentDetails();
        model.addAttribute("departments", departments);

        List<LocationWithCountryRegionDTO> allLocations = locationService.getAllLocations();
        model.addAttribute("locations", allLocations);

        List<Employees> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);

        List<Regions> allRegions = regionService.getAllRegions();
        model.addAttribute("regions", allRegions);
        return "departments";
    }

    @PostMapping("/")
    public String addDepartment(@ModelAttribute Department department, Model model) {
        departmentService.addDepartment(department);
        return "redirect:/departments-api/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int departmentId, Model model) {
        Department department = departmentService.getDepartmentById(departmentId);
        model.addAttribute("department", department);
        return "edit-department";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute("department") Department department) {
        departmentService.updateDepartment(department);
        return "redirect:/departments/";
    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") int departmentId) {
        departmentService.deleteDepartment(departmentId);
        return "redirect:/departments/";
    }

    @PostMapping(value = "/assign-manager", consumes = "application/json")
    public ResponseEntity<String> assignManager(@RequestBody Map<String, Integer> payload) {
        Integer departmentId = payload.get("departmentId");
        Integer managerId = payload.get("managerId");
        System.out.println("Dept Id :: " + departmentId + " " + managerId);
        departmentService.assignManager(departmentId, managerId);
        return ResponseEntity.ok("Manager assigned successfully");
    }

    @PostMapping(value = "/assign-location", consumes = "application/json")
    public ResponseEntity<String> assignLocation(@RequestBody Map<String, Integer> payload) {
        Integer departmentId = payload.get("departmentId");
        Integer locationId = payload.get("locationId");
        System.out.println("::> " + departmentId + " " + locationId);
        departmentService.assignLocation(departmentId, locationId);
        return ResponseEntity.ok("Location assigned successfully");
    }
}
