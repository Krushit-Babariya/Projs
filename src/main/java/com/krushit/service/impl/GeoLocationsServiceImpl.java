package com.nj.service.impl;

import com.nj.common.enums.GeoLocationType;
import com.nj.entity.GeoLocation;
import com.nj.repository.GeoLocationRepository;
import com.nj.service.IGeoLocationsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationsServiceImpl implements IGeoLocationsService {
    
    @Autowired
    private GeoLocationRepository geoLocationRepository;
    
    public GeoLocationsServiceImpl(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }
    
    @Override
    public List<String> getAllContinents(GeoLocationType type) {
        return geoLocationRepository.getAllContinents(type);
    }
    
    @Override
    public List<GeoLocation> getContinents() {
        return geoLocationRepository.getContinents();
    }
    
    @Override
    public List<GeoLocation> getCountriesByContinentId(int continentId) {
        return geoLocationRepository.getCountriesByContinentId(continentId);
    }
    
    @Override
    public List<GeoLocation> getStatesByCountryId(int countryId) {
        return geoLocationRepository.getStatesByCountryId(countryId);
    }
    
    @Override
    public List<GeoLocation> getCitiesByStateId(int stateId) {
        return geoLocationRepository.getCitiesByStateId(stateId);
    }
}
