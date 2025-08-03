package com.nj.common.validator;

import com.nj.entity.Employees;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employees.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employees emp = (Employees) target;

        if (emp.getFirstName() == null || emp.getFirstName().isBlank()) {
            errors.rejectValue("firstName", "emp.firstName.required", "First name is required");
        } else if (emp.getFirstName().length() < 2 || emp.getFirstName().length() > 20) {
            errors.rejectValue("firstName", "emp.firstName.length", "First name must be between 2 and 20 characters");
        }

        if (emp.getLastName() == null || emp.getLastName().isBlank()) {
            errors.rejectValue("lastName", "emp.lastName.required", "Last name is required");
        } else if (emp.getLastName().length() < 2 || emp.getLastName().length() > 20) {
            errors.rejectValue("lastName", "emp.lastName.length", "Last name must be between 2 and 20 characters");
        }

        if (emp.getEmail() == null || emp.getEmail().isBlank()) {
            errors.rejectValue("email", "emp.email.required", "Email is required");
        } else if (!emp.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            errors.rejectValue("email", "emp.email.invalid", "Invalid email format");
        }

        if (emp.getPhoneNumber() == null || emp.getPhoneNumber().isBlank()) {
            errors.rejectValue("phoneNumber", "emp.phone.required", "Phone number is required");
        } else if (emp.getPhoneNumber().length() != 10) {
            errors.rejectValue("phoneNumber", "emp.phone.invalid", "Enter valid phone number");
        }

        if (emp.getHireDate() == null) {
            errors.rejectValue("hireDate", "emp.hireDate.required", "Hire date is required");
        }

        if (String.valueOf(emp.getJobId()) == null || String.valueOf(emp.getJobId()).isBlank()) {
            errors.rejectValue("jobId", "emp.job.required", "Job ID is required");
        }

        if (String.valueOf(emp.getSalary()) == null) {
            errors.rejectValue("salary", "emp.salary.required", "Salary is required");
        } else if (emp.getSalary() < 10000 || emp.getSalary() > 200000) {
            errors.rejectValue("salary", "emp.salary.range", "Salary must be between 10,000 and 200,000");
        }

        if (emp.getDepartmentId() == null) {
            errors.rejectValue("departmentId", "emp.dept.required", "Department ID is required");
        }
    }
}
