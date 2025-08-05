package com.nj.controller;

import com.nj.dto.EmployeeDocsDTO;
import com.nj.entity.EmployeeDocs;
import com.nj.service.impl.EmployeesDocsServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/employee-docs")
public class EmployeesDocsController1 {
    private EmployeesDocsServiceImpl1 employeeDocsService;

    public EmployeesDocsController1(EmployeesDocsServiceImpl1 employeeDocsService) {
        this.employeeDocsService = employeeDocsService;
    }
    
    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("employeeDocsDTO", new EmployeeDocsDTO());
        return "upload-form";
    }

    @PostMapping("/upload")
    public String uploadFiles(@ModelAttribute EmployeeDocsDTO dto, Model model) {
        try {
            // Validate that at least one file is uploaded
            if ((dto.getPhotoPath() == null || dto.getPhotoPath().isEmpty())
                    && (dto.getResumePath() == null || dto.getResumePath().isEmpty())) {
                model.addAttribute("error", "Please select at least one file to upload");
                return "upload-form";
            }

            // Validate file types and sizes
            String validationError = validateFiles(dto);
            if (validationError != null) {
                model.addAttribute("error", validationError);
                return "upload-form";
            }

            // Save files using service
            EmployeeDocs savedDocs = employeeDocsService.saveEmployeeDocs(dto);
            model.addAttribute("success", "Files uploaded successfully!");
            model.addAttribute("employeeDocs", savedDocs);
        } catch (IOException e) {
            model.addAttribute("error", "Error uploading files: " + e.getMessage());
        }
        return "upload-form";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        try {
            // Check if file exists
            if (!employeeDocsService.fileExists(fileName)) {
                return ResponseEntity.notFound().build();
            }

            // Get file content
            byte[] fileContent = employeeDocsService.getFile(fileName);// Set headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(employeeDocsService.getContentType(fileName)));
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentLength(fileContent.length);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<byte[]> viewFile(@PathVariable String fileName) {
        try {
            // Check if file exists
            if (!employeeDocsService.fileExists(fileName)) {
                return ResponseEntity.notFound().build();
            }

            // Get file content
            byte[] fileContent = employeeDocsService.getFile(fileName);
            // Set headers for inline viewing

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(employeeDocsService.getContentType(fileName)));
            headers.setContentDispositionFormData("inline", fileName);
            headers.setContentLength(fileContent.length);
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/api/upload")
    @ResponseBody
    public ResponseEntity<?> uploadFilesAPI(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "resume", required = false) MultipartFile resume,
            @RequestParam("employeeId") int employeeId) {
        try {
            // Create DTO
            EmployeeDocsDTO dto = new EmployeeDocsDTO();
            dto.setPhotoPath(photo);
            dto.setResumePath(resume);
            dto.setEmployeeId(employeeId);

            // Validate files
            String validationError = validateFiles(dto);
            if (validationError != null) {
                return ResponseEntity.badRequest().body(new ApiResponse(false, validationError));
            }

            // Save files
            EmployeeDocs savedDocs = employeeDocsService.saveEmployeeDocs(dto);
            return ResponseEntity.ok(new ApiResponse(true, "Files uploaded successfully", savedDocs));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error uploading files: " + e.getMessage()));
        }
    }

    private String validateFiles(EmployeeDocsDTO dto) {
        // Maximum file size (5MB)
        long maxSize = 5 * 1024 * 1024;
        if (dto.getPhotoPath() != null && !dto.getPhotoPath().isEmpty()) {
            MultipartFile photo = dto.getPhotoPath();
            if (photo.getSize() > maxSize) {
                return "Photo file size must be less than 5MB";
            }
            if (!isValidImageFile(photo.getOriginalFilename())) {
                return "Photo must be in JPG, JPEG, PNG, or GIF format";
            }
        }

        if (dto.getResumePath() != null && !dto.getResumePath().isEmpty()) {
            MultipartFile resume = dto.getResumePath();
            if (resume.getSize() > maxSize) {
                return "Resume file size must be less than 5MB";
            }
            if (!isValidDocumentFile(resume.getOriginalFilename())) {
                return "Resume must be in PDF, DOC, or DOCX format";
            }
        }
        return null;
    }

    private boolean isValidImageFile(String fileName) {
        if (fileName == null) {
            return false;
        }
        String extension = fileName.toLowerCase();
        return extension.endsWith(".jpg") || extension.endsWith(".jpeg")
                || extension.endsWith(".png") || extension.endsWith(".gif");
    }

    private boolean isValidDocumentFile(String fileName) {
        if (fileName == null) {
            return false;
        }
        String extension = fileName.toLowerCase();
        return extension.endsWith(".pdf") || extension.endsWith(".doc")
                || extension.endsWith(".docx");
    }

    public static class ApiResponse {

        private boolean success;
        private String message;
        private Object data;

        public ApiResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public ApiResponse(boolean success, String message, Object data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
