package com.nj.entity;

public class Department {
    private int departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
    
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId=" + managerId + ", locationId=" + locationId + '}';
    }
}
