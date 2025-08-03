package com.nj.repository;

import com.nj.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user) {
        StringBuilder sql = new StringBuilder("INSERT INTO users (first_name, last_name, email, phone_number, pass, role) VALUES (?, ?, ?, ?, ?, ?)");
        jdbcTemplate.update(sql.toString(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhoneNumber(), user.getPass(), user.getRole().name());
    }

    public User findByEmail(String email) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users WHERE email = ?");
        List<User> users = jdbcTemplate.query(sql.toString(), new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
        return users.isEmpty() ? null : users.get(0);
    }
}

