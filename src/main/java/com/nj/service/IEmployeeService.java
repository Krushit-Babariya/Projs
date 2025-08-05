package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.Employees;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IEmployeeService {

    List<Employees> getAllEmployees() throws ApplicationException;

    void updateEmployee(Employees emp) throws ApplicationException;

    void deleteEmployee(int employeeId) throws ApplicationException;

    List<Employees> getManagersByDepartmentId(int departmentId) throws ApplicationException;

    void addEmployee(Employees emp) throws ApplicationException;

    Employees getEmployeeById(int id) throws ApplicationException;

    List<String> uploadEmployeeExcel(MultipartFile file) throws Exception;

    List<Employees> getEmployeesPagedSorted(int page, int pageSize, String sortBy, String sortDir) throws ApplicationException;
}
