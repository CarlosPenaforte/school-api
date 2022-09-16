package com.school.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Admin Not Found")
public class AdminNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public AdminNotFoundException(Long id) {
        super(String.format("Admin with id %s not found in the system.", id));
    }
}