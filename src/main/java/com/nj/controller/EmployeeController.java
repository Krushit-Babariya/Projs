package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
import com.nj.common.messages.Message;
import com.nj.common.validator.EmployeeValidator;
import com.nj.dto.DepartmentDetailsDTO;
import com.nj.entity.Employees;
import com.nj.entity.Jobs;
import com.nj.service.IDepartmentService;
import com.nj.service.IEmployeeService;
import com.nj.service.IJobService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    private IEmployeeService employeeService;
    private IDepartmentService departmentService;
    private IJobService jobService;
    private EmployeeValidator employeeValidator;

    public EmployeeController(IEmployeeService employeeService, IDepartmentService departmentService, IJobService jobService, EmployeeValidator employeeValidator) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobService = jobService;
        this.employeeValidator = employeeValidator;
    }

    @InitBinder("employee")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(employeeValidator);
    }

//    @GetMapping("/employees")
//    public String getAllEmployees(Model model) {
//        try {
//            List<Employees> employees = employeeService.getAllEmployees();
//            model.addAttribute("message", "Welcome..!");
//            model.addAttribute("employees", employees);
//            model.addAttribute("employee", new Employees());
//        } catch (DBException e) {
//            model.addAttribute("error", "Database error: " + e.getMessage());
//        } catch (ApplicationException e) {
//            model.addAttribute("error", "Application error: " + e.getMessage());
//        } catch (Exception e) {
//            model.addAttribute("error", "Unexpected error: " + e.getMessage());
//        }
//        return "employees";
//    }
    @GetMapping("/employees")
    public String getEmployeesSorted(
            @RequestParam(defaultValue = "employee_id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        int page = Message.Constants.PAGE;
        int pageSize = Message.Constants.PAGE_SIZE;
        try {
            List<Employees> employees = employeeService.getEmployeesPagedSorted(page, pageSize, sortBy, sortDir);
            model.addAttribute("employees", employees);
            model.addAttribute("employee", new Employees());
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("sortDir", sortDir);
        } catch (DBException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "employees";
    }

    @GetMapping("/employees/dept")
    public String getManagersByDepartmentId(@RequestParam("departmentId") String departmentId, Model model) {
        try {
            List<Employees> employees = employeeService.getManagersByDepartmentId(Integer.parseInt(departmentId));
            model.addAttribute("managers", employees);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid department ID format.");
        } catch (DBException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "employees";
    }

    @GetMapping("/employees/update")
    public String showEmployeeUpdate(@ModelAttribute("employee") Employees employee, @RequestParam("employeeId") int employeeId, Model model) {
        try {
            Employees emp = employeeService.getEmployeeById(employeeId);
            BeanUtils.copyProperties(emp, employee);
        } catch (DBException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "employee-edit";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@ModelAttribute("employee") Employees emp, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        try {
            employeeValidator.validate(emp, result);
            if (result.hasErrors()) {
                model.addAttribute("employee", emp);
                return "employee-edit";
            }
            employeeService.updateEmployee(emp);
            redirectAttributes.addFlashAttribute("message", "Employee updated successfully.");
        } catch (DBException e) {
            redirectAttributes.addFlashAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "redirect:/api/employees";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam int employeeId, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployee(employeeId);
            redirectAttributes.addFlashAttribute("message", "Employee deleted successfully.");
        } catch (DBException e) {
            redirectAttributes.addFlashAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "redirect:/api/employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        try {
            List<DepartmentDetailsDTO> departments = departmentService.getAllDepartmentDetails();
            model.addAttribute("departments", departments);
            List<Jobs> jobs = jobService.getAllJobs();
            model.addAttribute("jobs", jobs);
        } catch (DBException e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "add-employees";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employees emp, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        try {
            employeeValidator.validate(emp, result);
            if (result.hasErrors()) {
                model.addAttribute("employees", employeeService.getAllEmployees());
                return "employees";
            }
            employeeService.addEmployee(emp);
            redirectAttributes.addFlashAttribute("message", "Employee added successfully!");
        } catch (DBException e) {
            redirectAttributes.addFlashAttribute("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            redirectAttributes.addFlashAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Unexpected error: " + e.getMessage());
        }
        return "redirect:/api/employees";
    }

    @GetMapping("/report")
    public String generateReport(@RequestParam("type") String type, Map<String, Object> map) {
        try {
            List<Employees> empList = employeeService.getAllEmployees();
            map.put("empList", empList);
            if (type.equalsIgnoreCase("pdf")) {
                return "pdfreport";
            }
        } catch (DBException e) {
            map.put("error", "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            map.put("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            map.put("error", "Unexpected error: " + e.getMessage());
        }
        return "excelreport";
    }

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<String> errorMessages = employeeService.uploadEmployeeExcel(file);
            model.addAttribute("uploadResult", errorMessages);
        } catch (IOException e) {
            model.addAttribute("uploadResult", List.of("File processing error: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            model.addAttribute("uploadResult", List.of("Invalid file format: " + e.getMessage()));
        } catch (DBException e) {
            model.addAttribute("uploadResult", List.of("Database error: " + e.getMessage()));
        } catch (ApplicationException e) {
            model.addAttribute("uploadResult", List.of("Application error: " + e.getMessage()));
        } catch (Exception e) {
            model.addAttribute("uploadResult", List.of("Unexpected error occurred: " + e.getMessage()));
        }
        return "upload-result";
    }
}
