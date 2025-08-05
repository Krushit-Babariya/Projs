package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.dto.EmployeeDocsDTO;
import com.nj.entity.EmployeeDocs;
import java.util.List;

public interface IEmployeesDocsService {

    void saveEmployeeDocs(EmployeeDocsDTO dto) throws ApplicationException;

    void deleteEmployeeDocs(int id) throws ApplicationException;

    List<EmployeeDocs> getDocsByEmployeeId(int employeeId) throws ApplicationException;

    EmployeeDocs getDocById(int id) throws ApplicationException;

    String fetchResumePathByJsId(int id) throws ApplicationException;

    String fetchPhotoPathByJsId(int id) throws ApplicationException;
}
