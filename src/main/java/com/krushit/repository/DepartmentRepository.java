package com.nj.repository;

import com.nj.entity.Department;
import com.nj.dto.DepartmentDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DepartmentDetailsDTO> fetchAllDepartmentDetails() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d.department_id, d.department_name, ")
                .append("CONCAT(e.first_name, ' ', e.last_name) AS managerFullName, ")
                .append("l.street_address, l.city, ")
                .append("c.country_name, r.region_name ")
                .append("FROM departments d ")
                .append("LEFT JOIN employees e ON d.manager_id = e.employee_id ")
                .append("LEFT JOIN locations l ON d.location_id = l.location_id ")
                .append("LEFT JOIN countries c ON l.country_id = c.country_id ")
                .append("LEFT JOIN regions r ON c.region_id = r.region_id");

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DepartmentDetailsDTO.class));
    }

    public void addDepartment(Department department) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO departments (department_name, manager_id, location_id) ")
                .append("VALUES (?, ?, ?)");

        jdbcTemplate.update(sql.toString(),
                department.getDepartmentName(),
                department.getManagerId(),
                department.getLocationId()
        );
    }

    public Department findById(int departmentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM departments WHERE department_id = ?");

        return jdbcTemplate.queryForObject(sql.toString(),
                new BeanPropertyRowMapper<>(Department.class),
                departmentId);
    }

    public void update(Department department) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE departments ")
                .append("SET department_name = ?, manager_id = ?, location_id = ? ")
                .append("WHERE department_id = ?");

        jdbcTemplate.update(sql.toString(),
                department.getDepartmentName(),
                department.getManagerId(),
                department.getLocationId(),
                department.getDepartmentId());
    }

    public void delete(int departmentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM departments WHERE department_id = ?");

        jdbcTemplate.update(sql.toString(), departmentId);
    }

    public void updateManager(Integer departmentId, Integer managerId) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE departments SET manager_id = ? WHERE department_id = ?");
        jdbcTemplate.update(sql.toString(), managerId, departmentId);

        StringBuilder updateDepartmentId = new StringBuilder();
        updateDepartmentId.append("UPDATE employees SET department_id = ? WHERE employee_id = ?");
        jdbcTemplate.update(updateDepartmentId.toString(), departmentId, managerId);

        StringBuilder updateManagerId = new StringBuilder();
        updateManagerId.append("UPDATE employees SET manager_id = 0 WHERE employee_id = ?");
        jdbcTemplate.update(updateManagerId.toString(), managerId);
    }

    public void updateLocation(Integer departmentId, Integer locationId) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE departments SET location_id = ? WHERE department_id = ?");
        jdbcTemplate.update(sql.toString(), locationId, departmentId);
    }
}
