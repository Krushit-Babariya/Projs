package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.common.messages.Message;
import com.nj.entity.Employees;
import java.util.Arrays;
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

    public List<Employees> findAll() throws DBException {
        String sql = "SELECT employee_id, first_name, last_name, email, phone_number, hire_date, "
                + "job_id, salary, commission_pct, manager_id, department_id FROM employees";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            throw new DBException("Error while fetching all employees", e);
        }
    }

    public void updateEmployee(Employees emp) throws DBException {
        String sql = "UPDATE employees SET "
                + "first_name = ?, last_name = ?, email = ?, "
                + "phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, "
                + "manager_id = ?, department_id = ? "
                + "WHERE employee_id = ?";
        try {
            jdbcTemplate.update(sql,
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
        } catch (Exception e) {
            throw new DBException("Error while updating employee with ID: " + emp.getEmployeeId(), e);
        }
    }

    public void deleteEmployee(int employeeId) throws DBException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try {
            jdbcTemplate.update(sql, employeeId);
        } catch (Exception e) {
            throw new DBException("Error while deleting employee with ID: " + employeeId, e);
        }
    }

    public boolean isAnyEmployeeAssociated(int managerId) throws DBException {
        String sql = "SELECT COUNT(*) FROM employees WHERE manager_id = ?";
        try {
            if (jdbcTemplate.queryForObject(sql, Integer.class, managerId) > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new DBException("Error checking subordinates for manager ID: " + managerId, e);
        }
        return false;
    }

    public List<Employees> getManagersByDepartmentId(int departmentId) throws DBException {
        String sql = "SELECT e.employee_id, e.first_name, e.last_name "
                + "FROM employees e "
                + "JOIN departments d ON e.department_id = d.department_id "
                + "WHERE e.department_id = 0 AND d.department_name = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{departmentId}, rowMapper);
        } catch (Exception e) {
            throw new DBException("Error while fetching managers for department ID: " + departmentId, e);
        }
    }

    public void addEmployee(Employees emp) throws DBException {
        String sql = "INSERT INTO employees "
                + "(first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, department_id, manager_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
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
        } catch (Exception e) {
            throw new DBException("Error while adding new employee", e);
        }
    }

    public Employees getEmployeeById(int id) throws DBException {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Employees.class));
        } catch (Exception e) {
            throw new DBException("Error while fetching employee with ID: " + id, e);
        }
    }

    public List<Employees> findAllPagedSorted(int page, int size, String sortBy, String sortDir) throws DBException {
        List<String> allowedSortColumns = Arrays.asList("employee_id", "first_name", "last_name", "email", "hire_date", "salary");
        if (!allowedSortColumns.contains(sortBy)) {
            sortBy = Message.Constants.DEFAULT_SORT_BY;
        }
        if (!sortDir.equalsIgnoreCase("asc") && !sortDir.equalsIgnoreCase("desc")) {
            sortDir = Message.Constants.DEFAULT_SORTING_ORDER;
        }
        int offset = (page - 1) * size;
        StringBuilder sql = new StringBuilder("SELECT employee_id, first_name, last_name, email, phone_number, hire_date, ");
        sql.append("job_id, salary, commission_pct, manager_id, department_id ");
        sql.append("FROM employees ");
        sql.append("ORDER BY ").append(sortBy).append(" ").append(sortDir).append(" ");
        sql.append("LIMIT ? OFFSET ?");
        try {
            return jdbcTemplate.query(sql.toString(), rowMapper, size, offset);
        } catch (Exception e) {
            throw new DBException(Message.Database.ERROR_OCCUR_WHILE_GETTING_EMPLOYEE_RECORDS, e);
        }
    }
}
