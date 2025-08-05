package com.nj.service.impl;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Country;
import com.nj.dto.CountryWithRegionDTO;
import com.nj.repository.CountryRepository;
import com.nj.service.ICountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void addCountry(Country country) throws ApplicationException {
        countryRepository.addCountry(country);
    }

    @Override
    public List<CountryWithRegionDTO> getCountriesByRegionId(int regionId) throws ApplicationException{
        return countryRepository.findByRegionId(regionId);
    }

    @Override
    public List<CountryWithRegionDTO> getAllCountriesWithRegionNames() throws ApplicationException  {
        return countryRepository.getAllCountriesWithRegionNames();
    }
}
