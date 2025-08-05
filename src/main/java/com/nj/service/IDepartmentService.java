package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Department;
import com.nj.dto.DepartmentDetailsDTO;

import java.util.List;

public interface IDepartmentService {

    List<DepartmentDetailsDTO> getAllDepartmentDetails()throws ApplicationException ;

    void addDepartment(Department department)throws ApplicationException ;

    Department getDepartmentById(int departmentId)throws ApplicationException ;

    void updateDepartment(Department department)throws ApplicationException ;

    void deleteDepartment(int departmentId)throws ApplicationException ;

    void assignManager(Integer departmentId, Integer managerId)throws ApplicationException ;

    void assignLocation(Integer departmentId, Integer locationId)throws ApplicationException ;
}