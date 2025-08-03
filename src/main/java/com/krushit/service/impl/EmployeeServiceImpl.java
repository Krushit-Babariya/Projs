package com.nj.service.impl;

import com.nj.entity.Employees;
import com.nj.repository.EmployeeRepository;
import com.nj.service.IEmployeeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void updateEmployee(Employees emp) {
        employeeRepository.updateEmployee(emp);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }

    @Override
    public List<Employees> getManagersByDepartmentId(int departmentId) {
        return employeeRepository.getManagersByDepartmentId(departmentId);
    }

    @Override
    public void addEmployee(Employees emp) {
        employeeRepository.addEmployee(emp);
    }

    @Override
    public Employees getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<String> uploadEmployeeExcel(MultipartFile file) throws Exception {
        List<String> errors = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                errors.add("Row " + (i + 1) + " is empty.");
                continue;
            }

            try {
                Employees emp = new Employees();

                emp.setFirstName(getStringCellValue(row.getCell(0)));
                emp.setLastName(getStringCellValue(row.getCell(1)));
                emp.setEmail(getStringCellValue(row.getCell(2)));
                emp.setPhoneNumber(getStringCellValue(row.getCell(3)));
                emp.setHireDate(getDateCellValue(row.getCell(4)));
                emp.setJobId(getIntCellValue(row.getCell(5)));
                emp.setSalary(getDoubleCellValue(row.getCell(6)));
                emp.setCommissionPct(BigDecimal.valueOf(getDoubleCellValue(row.getCell(7))));
                emp.setManagerId(getIntCellValue(row.getCell(8)));
                emp.setDepartmentId(getIntCellValue(row.getCell(9)));

                addEmployee(emp);
            } catch (Exception e) {
                errors.add("Row " + (i + 1) + ": " + e.getMessage());
            }
        }

        workbook.close();
        return errors;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING ->
                cell.getStringCellValue().trim();
            case NUMERIC ->
                String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN ->
                String.valueOf(cell.getBooleanCellValue());
            default ->
                "";
        };
    }

    private double getDoubleCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        return switch (cell.getCellType()) {
            case NUMERIC ->
                cell.getNumericCellValue();
            case STRING -> {
                String val = cell.getStringCellValue().trim();
                if (val.isEmpty()) {
                    yield 0.0;
                }
                yield Double.parseDouble(val);
            }
            default ->
                0.0;
        };
    }

    private int getIntCellValue(Cell cell) {
        return (int) getDoubleCellValue(cell);
    }

    private Date getDateCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            // Handle ISO-style date string like "2004-01-01"
            try {
                return java.sql.Date.valueOf(cell.getStringCellValue().trim());
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid date format");
            }
        }
        throw new IllegalArgumentException("Expected date cell");
    }

}
