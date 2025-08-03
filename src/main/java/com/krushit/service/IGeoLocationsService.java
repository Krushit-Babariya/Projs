package com.nj.service;

import com.nj.common.enums.GeoLocationType;
import com.nj.entity.GeoLocation;
import java.util.List;

public interface IGeoLocationsService {

    List<String> getAllContinents(GeoLocationType type);

    List<GeoLocation> getContinents();

    List<GeoLocation> getCountriesByContinentId(int continentId);

    List<GeoLocation> getStatesByCountryId(int countryId);

    List<GeoLocation> getCitiesByStateId(int stateId);
}
