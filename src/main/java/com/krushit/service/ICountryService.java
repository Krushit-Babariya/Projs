package com.nj.service;

import com.nj.entity.Country;
import com.nj.dto.CountryWithRegionDTO;
import java.util.List;

public interface ICountryService {

    void addCountry(Country country);

    List<CountryWithRegionDTO> getAllCountriesWithRegionNames();

    List<CountryWithRegionDTO> getCountriesByRegionId(int regionId);
}
