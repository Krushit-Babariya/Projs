package com.nj.service.impl;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Regions;
import com.nj.repository.RegionRepository;
import com.nj.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {

    private RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Regions> getAllRegions() throws ApplicationException {
        return regionRepository.findAll();
    }

    @Override
    public Regions getRegionById(int id) throws ApplicationException {
        return regionRepository.findById(id);
    }

    @Override
    public void deleteRegionById(int id) throws ApplicationException {
        regionRepository.deleteById(id);
    }

    @Override
    public void addRegion(Regions region) throws ApplicationException {
        regionRepository.addRegion(region);
    }

    @Override
    public void changeStatus(int regionId) throws ApplicationException {
        regionRepository.changeStatus(regionId);
    }

    @Override
    public List<String> getAffectedLocations(int regionId) throws ApplicationException {
        return regionRepository.checkInactiveWarnings(regionId);
    }
}
