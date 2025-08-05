package com.nj.repository;

import com.nj.common.exception.DBException;
import com.nj.entity.User;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user) throws DBException {
        StringBuilder sql = new StringBuilder("INSERT INTO users (first_name, last_name, email, phone_number, pass, role) VALUES (?, ?, ?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getPass(),
                    user.getRole().name());
        } catch (Exception e) {
            throw new DBException("Error saving user: " + user.getEmail(), e);
        }
    }

    public User findByEmail(String email) throws DBException {
        StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE email = ?");
        try {
            List<User> users = jdbcTemplate.query(sql.toString(),
                    new Object[]{email},
                    new BeanPropertyRowMapper<>(User.class));
            return users.isEmpty() ? null : users.get(0);
        } catch (Exception e) {
            throw new DBException("Error fetching user by email: " + email, e);
        }
    }
}
