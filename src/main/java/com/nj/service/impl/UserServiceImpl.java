package com.nj.service.impl;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.User;
import com.nj.repository.UserRepository;
import com.nj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) throws ApplicationException {
        this.userRepository = userRepository;
    }

    public void signup(User user) throws ApplicationException {
        userRepository.saveUser(user);
    }

    public User login(String email, String password) throws ApplicationException {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPass().equals(password)) {
            return user;
        }
        return null;
    }
}
