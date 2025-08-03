package com.nj.service.impl;


import com.nj.entity.Location;
import com.nj.dto.LocationWithCountryRegionDTO;
import com.nj.repository.LocationRepository;
import com.nj.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements ILocationService {

    @Autowired
    private LocationRepository locationRepo;

    @Override
    public void addLocation(Location location) {
        locationRepo.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        locationRepo.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        locationRepo.updateLocation(location);
    }

    @Override
    public List<LocationWithCountryRegionDTO> getAllLocations() {
        return locationRepo.getAllLocationsWithCountryAndRegion();
    }
}
