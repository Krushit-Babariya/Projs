package com.nj.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeDocsDTO {

    private int id;
    private MultipartFile photoPath;
    private MultipartFile resumePath;
    private int employeeId;

    public EmployeeDocsDTO() {
    }

    public EmployeeDocsDTO(int id, MultipartFile photoPath, MultipartFile resumePath, int employeeId) {
        this.id = id;
        this.photoPath = photoPath;
        this.resumePath = resumePath;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(MultipartFile photoPath) {
        this.photoPath = photoPath;
    }

    public MultipartFile getResumePath() {
        return resumePath;
    }

    public void setResumePath(MultipartFile resumePath) {
        this.resumePath = resumePath;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    

    @Override
    public String toString() {
        return "EmployeeDoc{"
                + "id=" + id
                + ", photoPath='" + photoPath + '\''
                + ", resumePath='" + resumePath + '\''
                + ", employeeId=" + employeeId
                + '}';
    }
}
