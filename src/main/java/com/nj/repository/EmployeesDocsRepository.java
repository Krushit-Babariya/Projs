package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.entity.EmployeeDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class EmployeesDocsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<EmployeeDocs> rowMapper = (ResultSet rs, int rowNum) -> new EmployeeDocs(
            rs.getInt("id"),
            rs.getString("photo_path"),
            rs.getString("resume_path"),
            rs.getInt("employee_id")
    );

    public void addEmployeeDocs(EmployeeDocs docs) throws DBException {
        String sql = "INSERT INTO employees_docs (photo_path, resume_path, employee_id) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, docs.getPhotoPath(), docs.getResumePath(), docs.getEmployeeId());
        } catch (Exception e) {
            throw new DBException("Failed to add employee documents", e);
        }
    }

    public void updateEmployeeDocs(EmployeeDocs docs) throws DBException {
        String sql = "UPDATE employees_docs SET photo_path = ?, resume_path = ? WHERE employee_id = ?";
        try {
            jdbcTemplate.update(sql, docs.getPhotoPath(), docs.getResumePath(), docs.getEmployeeId());
        } catch (Exception e) {
            throw new DBException("Failed to update employee documents", e);
        }
    }

    public void deleteEmployeeDocs(int id) throws DBException {
        try {
            jdbcTemplate.update("DELETE FROM employees_docs WHERE id = ?", id);
        } catch (Exception e) {
            throw new DBException("Failed to delete employee documents with ID: " + id, e);
        }
    }

    public List<EmployeeDocs> getEmployeeDocsByEmployeeId(int employeeId) throws DBException {
        try {
            return jdbcTemplate.query("SELECT * FROM employees_docs WHERE employee_id = ?", rowMapper, employeeId);
        } catch (Exception e) {
            throw new DBException("Failed to fetch documents for employee ID: " + employeeId, e);
        }
    }

    public EmployeeDocs getEmployeeDocsById(int id) throws DBException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM employees_docs WHERE id = ?", rowMapper, id);
        } catch (Exception e) {
            throw new DBException("Failed to fetch document by ID: " + id, e);
        }
    }

    public String getResumePathByEmployeeId(int employeeId) throws DBException {
        String sql = "SELECT resume_path FROM employees_docs WHERE employee_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, employeeId);
        } catch (Exception e) {
            throw new DBException("Failed to fetch resume path for employee ID: " + employeeId, e);
        }
    }

    public String getPhotoPathByEmployeeId(int employeeId) throws DBException {
        String sql = "SELECT photo_path FROM employees_docs WHERE employee_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, employeeId);
        } catch (Exception e) {
            throw new DBException("Failed to fetch photo path for employee ID: " + employeeId, e);
        }
    }
}