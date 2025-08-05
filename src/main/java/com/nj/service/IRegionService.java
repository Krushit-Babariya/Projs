package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Regions;
import java.util.List;

public interface IRegionService {

    List<Regions> getAllRegions() throws ApplicationException;

    Regions getRegionById(int id) throws ApplicationException;

    void deleteRegionById(int id) throws ApplicationException;

    void addRegion(Regions region) throws ApplicationException;

    void changeStatus(int regionId) throws ApplicationException;

    List<String> getAffectedLocations(int regionId) throws ApplicationException;
}
