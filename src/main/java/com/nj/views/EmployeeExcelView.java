package com.nj.views;

import com.nj.entity.Employees;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Component("excelreport")
public class EmployeeExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Employees> empsList = (List<Employees>) map.get("empList");
        Sheet sheet1 = workbook.createSheet("Employees");

        Row header = sheet1.createRow(0);
        header.createCell(0).setCellValue("Employee ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Email");
        header.createCell(3).setCellValue("Phone Number");
        header.createCell(4).setCellValue("Hire Date");
        header.createCell(5).setCellValue("Salary");
        header.createCell(6).setCellValue("Manager ID");
        header.createCell(7).setCellValue("Department ID");

        // employee data
        int i = 1;
        for (Employees emp : empsList) {
            Row row = sheet1.createRow(i);

            row.createCell(0).setCellValue(emp.getEmployeeId());
            row.createCell(1).setCellValue(emp.getFirstName() + " " + emp.getLastName());
            row.createCell(2).setCellValue(emp.getEmail());
            row.createCell(3).setCellValue(emp.getPhoneNumber());
            row.createCell(4).setCellValue(String.valueOf(emp.getHireDate()));
            row.createCell(5).setCellValue(emp.getSalary());

            if (emp.getManagerId() != null) {
                row.createCell(6).setCellValue(emp.getManagerId());
            } else {
                row.createCell(6).setCellValue("N/A");
            }

            if (emp.getDepartmentId() != null) {
                row.createCell(7).setCellValue(emp.getDepartmentId());
            } else {
                row.createCell(7).setCellValue("N/A");
            }
            i++;
        }
    }
}
