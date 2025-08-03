package com.nj.service;

import com.nj.dto.EmployeeDocsDTO;
import com.nj.entity.EmployeeDocs;
import java.util.List;

public interface IEmployeesDocsService {

    void saveEmployeeDocs(EmployeeDocsDTO dto);

    void deleteEmployeeDocs(int id);

    List<EmployeeDocs> getDocsByEmployeeId(int employeeId);

    EmployeeDocs getDocById(int id);

    String fetchResumePathByJsId(int id);

    String fetchPhotoPathByJsId(int id);
}
