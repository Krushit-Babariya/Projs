package com.nj.service;

import com.nj.entity.Department;
import com.nj.dto.DepartmentDetailsDTO;

import java.util.List;

public interface IDepartmentService {

    List<DepartmentDetailsDTO> getAllDepartmentDetails();

    void addDepartment(Department department);

    Department getDepartmentById(int departmentId);

    void updateDepartment(Department department);

    void deleteDepartment(int departmentId);

    void assignManager(Integer departmentId, Integer managerId);

    void assignLocation(Integer departmentId, Integer locationId);
}
