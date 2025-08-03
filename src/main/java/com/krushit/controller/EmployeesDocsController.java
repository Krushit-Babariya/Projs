package com.nj.controller;

import com.nj.dto.EmployeeDocsDTO;
import com.nj.entity.EmployeeDocs;
import com.nj.service.impl.EmployeesDocsServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.commons.io.IOUtils;

@Controller
@RequestMapping("/employee-docs-api")
public class EmployeesDocsController {

    @Autowired
    private EmployeesDocsServiceImpl service;

    @GetMapping("/")
    public String home(Model model, @RequestParam("employeeId") int employeeId) {
        List<EmployeeDocs> docs = service.getDocsByEmployeeId(employeeId);
        model.addAttribute("docs", docs);
        model.addAttribute("employeeId", employeeId);
        return "employees-docs";
    }

    @PostMapping("/add")
    public String addEmployeeDocs(@ModelAttribute("ed") EmployeeDocsDTO dto) {
        service.saveEmployeeDocs(dto);
        return "redirect:/employee-docs-api/list?employeeId=" + dto.getEmployeeId();
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeDocs(@PathVariable int id, @RequestParam int employeeId) {
        service.deleteEmployeeDocs(id);
        return "redirect:/employee-docs-api/list?employeeId=" + employeeId;
    }

    @GetMapping("/list")
    public String listDocs(@RequestParam int employeeId, Model model) {
        List<EmployeeDocs> docs = service.getDocsByEmployeeId(employeeId);
        model.addAttribute("docs", docs);
        model.addAttribute("employeeId", employeeId);
        return "employees-docs";
    }

    @Autowired
    private ServletContext sc;

    @GetMapping("/download")
    public void fileDownload(HttpServletResponse res,
            @RequestParam("jsId") int id,
            @RequestParam("type") String type) throws Exception {

        String filePath;
        if ("resume".equalsIgnoreCase(type)) {
            filePath = service.fetchResumePathByJsId(id);
        } else {
            filePath = service.fetchPhotoPathByJsId(id);
        }
        
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not exits");
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        res.setContentLengthLong(file.length());

        String mimeType = sc.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        res.setContentType(mimeType);

        res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (InputStream is = new FileInputStream(file); OutputStream os = res.getOutputStream()) {
            IOUtils.copy(is, os);
            os.flush();
        }
    }
}
