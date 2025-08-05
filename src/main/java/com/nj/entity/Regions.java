package com.nj.entity;

public class Regions {
    private int regionId;
    private String regionName;
    private boolean isActive;

    public Regions() {
    }

    public Regions(int regionId, String regionName, boolean isActive) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.isActive = isActive;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}