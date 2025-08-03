package com.nj.entity;

public class EmployeeDocs {

    private int id;
    private String photoPath;
    private String resumePath;
    private int employeeId;

    public EmployeeDocs() {
    }

    public EmployeeDocs(int id, String photoPath, String resumePath, int employeeId) {
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
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
