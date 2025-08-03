package com.nj.repository;

import com.nj.entity.Employees;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class EmployeeRepository {

    private final RowMapper<Employees> rowMapper = (rs, num) -> {
        Employees emp = new Employees();
        emp.setEmployeeId(rs.getInt("employee_id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setEmail(rs.getString("email"));
        emp.setPhoneNumber(rs.getString("phone_number"));
        emp.setHireDate(rs.getDate("hire_date"));
        emp.setJobId(rs.getInt("job_id"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setCommissionPct(rs.getBigDecimal("commission_pct"));
        emp.setManagerId(rs.getInt("manager_id"));
        emp.setDepartmentId(rs.getInt("department_id"));
        return emp;
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employees> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT employee_id, first_name, last_name, email, phone_number, hire_date, ");
        sql.append("job_id, salary, commission_pct, manager_id, department_id ");
        sql.append("FROM employees");

        return jdbcTemplate.query(sql.toString(), rowMapper);
    }

    public void updateEmployee(Employees emp) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE employees SET ");
        sql.append("first_name = ?, last_name = ?, email = ?, ");
        sql.append("phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, ");
        sql.append("manager_id = ?, department_id = ? ");
        sql.append("WHERE employee_id = ?");

        jdbcTemplate.update(sql.toString(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhoneNumber(),
                emp.getHireDate(),
                emp.getJobId(),
                emp.getSalary(),
                emp.getCommissionPct(),
                emp.getManagerId(),
                emp.getDepartmentId(),
                emp.getEmployeeId());
    }

    public void deleteEmployee(int employeeId) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM employees WHERE employee_id = ?");
        jdbcTemplate.update(sql.toString(), employeeId);
    }

    public List<Employees> getManagersByDepartmentId(int departmentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e.employee_id, e.first_name, e.last_name ");
        sql.append("FROM employees e ");
        sql.append("JOIN departments d ON e.department_id = d.department_id ");
        sql.append("WHERE e.department_id = 0 AND d.department_name = ?");

        return jdbcTemplate.query(sql.toString(), new Object[]{departmentId}, rowMapper);
    }

    public void addEmployee(Employees emp) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO employees ");
        sql.append("(first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, department_id, manager_id) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        jdbcTemplate.update(sql.toString(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhoneNumber(),
                emp.getHireDate(),
                emp.getJobId(),
                emp.getSalary(),
                emp.getCommissionPct(),
                emp.getDepartmentId(),
                emp.getManagerId());
    }

    public Employees getEmployeeById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM employees WHERE employee_id = ?");
        return jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Employees.class));
    }
}
