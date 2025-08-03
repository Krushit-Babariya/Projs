package com.nj.service.impl;

import com.nj.entity.Regions;
import com.nj.repository.RegionRepository;
import com.nj.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Regions> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Regions getRegionById(int id) {
        return regionRepository.findById(id);
    }

    @Override
    public void deleteRegionById(int id) {
        regionRepository.deleteById(id);
    }
    
    @Override
    public void addRegion(Regions region) {
        regionRepository.addRegion(region);
    }
}
