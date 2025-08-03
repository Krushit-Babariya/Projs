package com.nj.service;


import com.nj.entity.Location;
import com.nj.dto.LocationWithCountryRegionDTO;
import java.util.List;

public interface ILocationService {
    void addLocation(Location location);
    void deleteLocation(int locationId);
    void updateLocation(Location location);
    List<LocationWithCountryRegionDTO> getAllLocations();
}
