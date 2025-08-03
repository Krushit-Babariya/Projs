package com.nj.service;

import com.nj.entity.Employees;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IEmployeeService {

    List<Employees> getAllEmployees();

    void updateEmployee(Employees emp);

    void deleteEmployee(int employeeId);

    List<Employees> getManagersByDepartmentId(int departmentId);

    void addEmployee(Employees emp);

    Employees getEmployeeById(int id);

    List<String> uploadEmployeeExcel(MultipartFile file) throws Exception;
}
