package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.entity.Department;
import com.nj.dto.DepartmentDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DepartmentDetailsDTO> fetchAllDepartmentDetails() throws DBException {
        try {
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
        } catch (Exception e) {
            throw new DBException("Error fetching department details", e);
        }
    }

    public void addDepartment(Department department) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO departments (department_name, manager_id, location_id) ")
                    .append("VALUES (?, ?, ?)");
            jdbcTemplate.update(sql.toString(),
                    department.getDepartmentName(),
                    department.getManagerId(),
                    department.getLocationId()
            );
        } catch (Exception e) {
            throw new DBException("Error adding department", e);
        }
    }

    public Department findById(int departmentId) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM departments WHERE department_id = ?");
            return jdbcTemplate.queryForObject(sql.toString(),
                    new BeanPropertyRowMapper<>(Department.class),
                    departmentId);
        } catch (Exception e) {
            throw new DBException("Error finding department with ID: " + departmentId, e);
        }
    }

    public void update(Department department) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE departments ")
                    .append("SET department_name = ?, manager_id = ?, location_id = ? ")
                    .append("WHERE department_id = ?");

            jdbcTemplate.update(sql.toString(),
                    department.getDepartmentName(),
                    department.getManagerId(),
                    department.getLocationId(),
                    department.getDepartmentId());
        } catch (Exception e) {
            throw new DBException("Error updating department with ID: " + department.getDepartmentId(), e);
        }
    }

    public void delete(int departmentId) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM departments WHERE department_id = ?");
            jdbcTemplate.update(sql.toString(), departmentId);
        } catch (Exception e) {
            throw new DBException("Error deleting department with ID: " + departmentId, e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateManager(Integer departmentId, Integer managerId) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE departments SET manager_id = ? WHERE department_id = ?");
            jdbcTemplate.update(sql.toString(), managerId, departmentId);

            StringBuilder updateDepartmentId = new StringBuilder();
            updateDepartmentId.append("UPDATE employees SET department_id = ? WHERE employee_id = ?");
            jdbcTemplate.update(updateDepartmentId.toString(), departmentId, managerId);

            StringBuilder updateManagerId = new StringBuilder();
            updateManagerId.append("UPDATE employees SET manager_id = 0 WHERE employee_id = ?");
            jdbcTemplate.update(updateManagerId.toString(), managerId);
        } catch (Exception e) {
            throw new DBException("Error updating manager for department ID: " + departmentId, e);
        }
    }

    public void updateLocation(Integer departmentId, Integer locationId) throws DBException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE departments SET location_id = ? WHERE department_id = ?");
            jdbcTemplate.update(sql.toString(), locationId, departmentId);
        } catch (Exception e) {
            throw new DBException("Error updating location for department ID: " + departmentId, e);
        }
    }
}
