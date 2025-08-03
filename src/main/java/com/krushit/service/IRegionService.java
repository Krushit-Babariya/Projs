package com.nj.service;

import com.nj.entity.Regions;
import java.util.List;

public interface IRegionService {
    List<Regions> getAllRegions();
    Regions getRegionById(int id);
    void deleteRegionById(int id);
    void addRegion(Regions region);
}