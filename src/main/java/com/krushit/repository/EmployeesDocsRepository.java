package com.nj.repository;

import com.nj.entity.EmployeeDocs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeesDocsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<EmployeeDocs> rowMapper = new RowMapper<>() {
        public EmployeeDocs mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EmployeeDocs(
                    rs.getInt("id"),
                    rs.getString("photo_path"),
                    rs.getString("resume_path"),
                    rs.getInt("employee_id")
            );
        }
    };

    public void addEmployeeDocs(EmployeeDocs docs) {
        String sql = "INSERT INTO employees_docs (photo_path, resume_path, employee_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, docs.getPhotoPath(), docs.getResumePath(), docs.getEmployeeId());
    }

    public void updateEmployeeDocs(EmployeeDocs docs) {
        String sql = "UPDATE employees_docs SET photo_path = ?, resume_path = ? WHERE employee_id = ?";
        jdbcTemplate.update(sql, docs.getPhotoPath(), docs.getResumePath(), docs.getEmployeeId());
    }

    public void deleteEmployeeDocs(int id) {
        jdbcTemplate.update("DELETE FROM employees_docs WHERE id = ?", id);
    }

    public List<EmployeeDocs> getEmployeeDocsByEmployeeId(int employeeId) {
        return jdbcTemplate.query("SELECT * FROM employees_docs WHERE employee_id = ?", rowMapper, employeeId);
    }

    public EmployeeDocs getEmployeeDocsById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employees_docs WHERE id = ?", rowMapper, id);
    }

    public String getResumePathByEmployeeId(int employeeId) {
        String sql = "SELECT resume_path FROM employees_docs WHERE employee_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, employeeId);
    }

    public String getPhotoPathByEmployeeId(int employeeId) {
        String sql = "SELECT photo_path FROM employees_docs WHERE employee_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, employeeId);
    }
}
