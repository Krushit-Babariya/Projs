package com.nj.common.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

//@Component
public class AuthException extends ApplicationException {

    public AuthException(String message) {
        super(message);
    }
}
