package com.nj.service.impl;


import com.nj.common.exception.ApplicationException;
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
    public void addLocation(Location location) throws ApplicationException {
        locationRepo.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) throws ApplicationException {
        locationRepo.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) throws ApplicationException {
        locationRepo.updateLocation(location);
    }

    @Override
    public List<LocationWithCountryRegionDTO> getAllLocations() throws ApplicationException {
        return locationRepo.getAllLocationsWithCountryAndRegion();
    }
}
