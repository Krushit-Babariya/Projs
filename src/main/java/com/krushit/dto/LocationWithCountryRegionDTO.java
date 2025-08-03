package com.nj.dto;

public class LocationWithCountryRegionDTO {
    private int locationId;
    private String streetAddress;
    private int postalCode;
    private String city;
    private String stateProvince;
    private String countryName;
    private String regionName;

    public LocationWithCountryRegionDTO() {
    }
    
    public LocationWithCountryRegionDTO(int locationId, String streetAddress, int postalCode, String city, String stateProvince, String countryName, String regionName) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.countryName = countryName;
        this.regionName = regionName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
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
        return "LocationWithCountryRegion{" + "locationId=" + locationId + ", streetAddress=" + streetAddress + ", postalCode=" + postalCode + ", city=" + city + ", stateProvince=" + stateProvince + ", countryName=" + countryName + ", regionName=" + regionName + '}';
    }
}
