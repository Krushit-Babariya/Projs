package com.nj.service;

import com.nj.entity.User;

public interface IUserService {
    void signup(User user);
    User login(String email, String password);
}
