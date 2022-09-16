package com.school.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Admin Already Registered")
public class AdminAlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public AdminAlreadyRegisteredException(Long id) {
        super(String.format("Admin with id %s is already registered in the system.", id));
    }
}
