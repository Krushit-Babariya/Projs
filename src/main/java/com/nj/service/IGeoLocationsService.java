package com.nj.service;

import com.nj.common.enums.GeoLocationType;
import com.nj.common.exception.ApplicationException;
import com.nj.entity.GeoLocation;
import java.util.List;

public interface IGeoLocationsService {

    List<String> getAllContinents(GeoLocationType type) throws ApplicationException;

    List<GeoLocation> getContinents() throws ApplicationException;

    List<GeoLocation> getCountriesByContinentId(int continentId) throws ApplicationException;

    List<GeoLocation> getStatesByCountryId(int countryId) throws ApplicationException;

    List<GeoLocation> getCitiesByStateId(int stateId) throws ApplicationException;
}
