package com.nj.dto;


public class DepartmentDetailsDTO {
    private int departmentId;
    private String departmentName;
    private String managerFullName;
    private String streetAddress;
    private String city;
    private String countryName;
    private String regionName;
    
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

    public String getManagerFullName() {
        return managerFullName;
    }

    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "DepartmentDetailsDTO{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerFullName=" + managerFullName + ", streetAddress=" + streetAddress + ", city=" + city + ", countryName=" + countryName + ", regionName=" + regionName + '}';
    }
}
