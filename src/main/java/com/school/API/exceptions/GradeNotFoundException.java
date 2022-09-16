package com.school.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Grade Not Found")
public class GradeNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public GradeNotFoundException(Long id) {
        super(String.format("Grade with id %s not found in the system.", id));
    }
}
