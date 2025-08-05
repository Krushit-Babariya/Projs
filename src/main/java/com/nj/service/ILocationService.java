package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Location;
import com.nj.dto.LocationWithCountryRegionDTO;
import java.util.List;

public interface ILocationService {

    void addLocation(Location location) throws ApplicationException;

    void deleteLocation(int locationId) throws ApplicationException;

    void updateLocation(Location location) throws ApplicationException;

    List<LocationWithCountryRegionDTO> getAllLocations() throws ApplicationException;
}
