package com.nj.service.impl;

import com.nj.entity.Department;
import com.nj.dto.DepartmentDetailsDTO;
import com.nj.repository.DepartmentRepository;
import com.nj.service.IDepartmentService;
import com.nj.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDetailsDTO> getAllDepartmentDetails() {
        return departmentRepository.fetchAllDepartmentDetails();
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.addDepartment(department);
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentRepository.update(department);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        departmentRepository.delete(departmentId);
    }

    @Override
    public void assignManager(Integer departmentId, Integer managerId) {
        departmentRepository.updateManager(departmentId, managerId);
    }

    @Override
    public void assignLocation(Integer departmentId, Integer locationId) {
        departmentRepository.updateLocation(departmentId, locationId);
    }
}
