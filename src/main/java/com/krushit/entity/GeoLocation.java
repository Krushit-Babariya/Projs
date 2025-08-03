package com.nj.entity;

import com.nj.common.enums.GeoLocationType;

public class GeoLocation {
    private int id;
    private String name;
    private GeoLocationType geoLocationType;
    private int parentId;

    public GeoLocation() {
    }

    public GeoLocation(int id, String name, GeoLocationType geoLocationType, int parentId) {
        this.id = id;
        this.name = name;
        this.geoLocationType = geoLocationType;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoLocationType getGeoLocationType() {
        return geoLocationType;
    }

    public void setGeoLocationType(GeoLocationType geoLocationType) {
        this.geoLocationType = geoLocationType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "GeoLocation{" + "id=" + id + ", name=" + name + ", geoLocationType=" + geoLocationType + ", parentId=" + parentId + '}';
    }
}
