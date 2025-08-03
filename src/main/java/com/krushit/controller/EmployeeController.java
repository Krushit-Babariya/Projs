package com.nj.controller;

import com.nj.common.validator.EmployeeValidator;
import com.nj.dto.DepartmentDetailsDTO;
import com.nj.entity.Employees;
import com.nj.entity.Jobs;
import com.nj.service.IDepartmentService;
import com.nj.service.IEmployeeService;
import com.nj.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    private IEmployeeService employeeService;

    private IDepartmentService departmentService;

    private IJobService jobService;

    private EmployeeValidator employeeValidator;

    @Autowired
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

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employees> employees = employeeService.getAllEmployees();
        model.addAttribute("message", "Welcome..!");
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employees());
        return "employees";
    }

    @GetMapping("/employees/dept")
    public String getManagersByDepartmentId(@RequestParam("departmentId") String departmentId, Model model) {
        System.out.println("department ID : " + departmentId);
        List<Employees> employees = employeeService.getManagersByDepartmentId(Integer.parseInt(departmentId));
        model.addAttribute("managers", employees);
        return "employees";
    }

    @GetMapping("/employees/update")
    public String showEmployeeUpdate(@ModelAttribute("employee") Employees employee, @RequestParam("employeeId") int employeeId) {
        Employees emp = employeeService.getEmployeeById(employeeId);
        BeanUtils.copyProperties(emp, employee);
        return "/employee-edit";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@ModelAttribute("employee") Employees emp, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        employeeValidator.validate(emp, result);
        if (result.hasErrors()) {
            model.addAttribute("employee", emp);
            return "employee-edit";
        }
        employeeService.updateEmployee(emp);
        redirectAttributes.addFlashAttribute("message", "Employee updated successfully.");
        return "redirect:/api/employees";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam int employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/api/employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        List<DepartmentDetailsDTO> departments = departmentService.getAllDepartmentDetails();
        model.addAttribute("departments", departments);

        List<Jobs> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "add-employees";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employees emp, BindingResult result, Model model) {
        employeeValidator.validate(emp, result);
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "employees";
        }
        employeeService.addEmployee(emp);
        model.addAttribute("message", "Employee added successfully!");
        return "redirect:/api/employees";
    }

    @GetMapping("/report")
    public String generateReport(@RequestParam("type") String type, Map<String, Object> map) {
        List<Employees> empList = employeeService.getAllEmployees();
        map.put("empList", empList);
        if (type.equalsIgnoreCase("pdf")) {
            return "pdfreport";
        }
        return "excelreport";
    }

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<String> errorMessages = employeeService.uploadEmployeeExcel(file);
            model.addAttribute("uploadResult", errorMessages);
        } catch (Exception e) {
            model.addAttribute("uploadResult", List.of("Failed to process file: " + e.getMessage()));
        }
        return "upload-result";
    }
}
