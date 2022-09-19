package com.school.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super(String.format("User with id %s not found in the system.", id));
    }

    public UserNotFoundException(String username) {
        super(String.format("User with username %s not found in the system.", username));
    }
}