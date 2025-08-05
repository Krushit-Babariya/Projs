package com.nj.service;

import com.nj.common.exception.ApplicationException;
import com.nj.entity.User;

public interface IUserService {

    void signup(User user) throws ApplicationException;

    User login(String email, String password) throws ApplicationException;
}
