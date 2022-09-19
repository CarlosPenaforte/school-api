package com.school.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Student Already Registered")
public class StudentAlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1L;

    public StudentAlreadyRegisteredException(Long id) {
        super(String.format("Student with id %s is already registered in the system.", id));
    }

    public StudentAlreadyRegisteredException(String name) {
        super(String.format("Student with name %s is already registered in the system.", name));
    }
}
