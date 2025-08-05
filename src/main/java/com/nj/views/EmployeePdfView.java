package com.nj.views;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nj.entity.Employees;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.Map;

@Component("pdfreport")
public class EmployeePdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Employees> empsList = (List<Employees>) model.get("empList");

        Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
        Paragraph title = new Paragraph("Employee Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // 8 columns
        PdfPTable table = new PdfPTable(8);
        table.setSpacingBefore(5);

        // Table headers
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Phone");
        table.addCell("Hire Date");
        table.addCell("Salary");
        table.addCell("Manager ID");
        table.addCell("Department ID");

        for (Employees emp : empsList) {
            table.addCell(String.valueOf(emp.getEmployeeId()));
            table.addCell(emp.getFirstName() + " " + emp.getLastName());
            table.addCell(emp.getEmail());
            table.addCell(emp.getPhoneNumber());
            table.addCell(String.valueOf(emp.getHireDate()));
            table.addCell(String.valueOf(emp.getSalary()));
            table.addCell(String.valueOf(emp.getManagerId()));
            table.addCell(String.valueOf(emp.getDepartmentId()));
        }

        document.add(table);
    }
}
