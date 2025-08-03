package com.nj.service.impl;

import com.nj.dto.EmployeeDocsDTO;
import com.nj.entity.EmployeeDocs;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeesDocsServiceImpl1 {

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    public EmployeeDocs saveEmployeeDocs(EmployeeDocsDTO dto) throws IOException {
        EmployeeDocs employeeDocs = new EmployeeDocs();
        employeeDocs.setEmployeeId(dto.getEmployeeId());

        // Create upload directory if it doesn't exist
        createUploadDirectoryIfNotExists();

        // Save photo if provided
        if (dto.getPhotoPath() != null && !dto.getPhotoPath().isEmpty()) {
            String photoPath = saveFile(dto.getPhotoPath(), "photo");
            employeeDocs.setPhotoPath(photoPath);
        }

        // Save resume if provided
        if (dto.getResumePath() != null && !dto.getResumePath().isEmpty()) {
            String resumePath = saveFile(dto.getResumePath(), "resume");
            employeeDocs.setResumePath(resumePath);
        }
        return employeeDocs;
    }

    private String saveFile(MultipartFile file, String type) throws IOException {
        // Generate unique filename
        String fileName = generateUniqueFileName(file.getOriginalFilename(), type);

        // Create full path
        Path filePath = Paths.get(uploadDir, fileName);

        // Save file to disk
        Files.copy(file.getInputStream(), filePath);
        return fileName; // Return just filename, not full path
    }

    private String generateUniqueFileName(String originalFileName, String type) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String extension = getFileExtension(originalFileName);
        return type + "_" + timestamp + "_" + System.currentTimeMillis() + extension;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    private void createUploadDirectoryIfNotExists() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public byte[] getFile(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir, fileName);
        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + fileName);
        }
        return Files.readAllBytes(filePath);
    }

    public boolean fileExists(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        Path filePath = Paths.get(uploadDir, fileName);
        return Files.exists(filePath);
    }

    public String getContentType(String fileName) {
        String extension = getFileExtension(fileName).toLowerCase();
        switch (extension) {
            case ".jpg":
            case ".jpeg":
                return "image/jpeg";
            case ".png":
                return "image/png";
            case ".gif":
                return "image/gif";
            case ".pdf":
                return "application/pdf";
            case ".doc":
                return "application/msword";
            case ".docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            default:
                return "application/octet-stream";
        }
    }
}
