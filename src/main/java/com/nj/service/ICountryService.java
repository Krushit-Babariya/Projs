package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Country;
import com.nj.dto.CountryWithRegionDTO;
import java.util.List;

public interface ICountryService {

    void addCountry(Country country) throws ApplicationException;

    List<CountryWithRegionDTO> getAllCountriesWithRegionNames() throws ApplicationException;

    List<CountryWithRegionDTO> getCountriesByRegionId(int regionId) throws ApplicationException;
}
