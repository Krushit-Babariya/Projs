package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.common.exception.DBException;
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

    private EmployeesDocsServiceImpl service;
    private ServletContext sc;

    public EmployeesDocsController(EmployeesDocsServiceImpl service, ServletContext sc) {
        this.service = service;
        this.sc = sc;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam("employeeId") int employeeId) {
        try {
            List<EmployeeDocs> docs = service.getDocsByEmployeeId(employeeId);
            model.addAttribute("docs", docs);
            model.addAttribute("employeeId", employeeId);
        } catch (DBException e) {
            model.addAttribute("error", "Database error while fetching employee documents: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred: " + e.getMessage());
        }
        return "employees-docs";
    }

    @PostMapping("/add")
    public String addEmployeeDocs(@ModelAttribute("ed") EmployeeDocsDTO dto, Model model) {
        try {
            service.saveEmployeeDocs(dto);
            return "redirect:/employee-docs-api/list?employeeId=" + dto.getEmployeeId();
        } catch (DBException e) {
            model.addAttribute("error", "Database error while saving document: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error: " + e.getMessage());
        }
        model.addAttribute("employeeId", dto.getEmployeeId());
        return "employees-docs";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeDocs(@PathVariable int id, @RequestParam int employeeId, Model model) {
        try {
            service.deleteEmployeeDocs(id);
        } catch (DBException e) {
            model.addAttribute("error", "Database error while deleting document: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred: " + e.getMessage());
        }
        return "redirect:/employee-docs-api/list?employeeId=" + employeeId;
    }

    @GetMapping("/list")
    public String listDocs(@RequestParam int employeeId, Model model) {
        try {
            List<EmployeeDocs> docs = service.getDocsByEmployeeId(employeeId);
            model.addAttribute("docs", docs);
            model.addAttribute("employeeId", employeeId);
        } catch (DBException e) {
            model.addAttribute("error", "Database error while loading document list: " + e.getMessage());
        } catch (ApplicationException e) {
            model.addAttribute("error", "Application error: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error occurred: " + e.getMessage());
        }
        return "employees-docs";
    }

    @GetMapping("/download")
    public void fileDownload(HttpServletResponse res,
            @RequestParam("jsId") int id,
            @RequestParam("type") String type) throws Exception {
        try {
            String filePath;
            if ("resume".equalsIgnoreCase(type)) {
                filePath = service.fetchResumePathByJsId(id);
            } else {
                filePath = service.fetchPhotoPathByJsId(id);
            }

            File file = new File(filePath);
            if (!file.exists()) {
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

        } catch (DBException e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } catch (ApplicationException e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Application error: " + e.getMessage());
        } catch (Exception e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage());
        }
    }
}
