package com.stocktrader.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User Already Registered")
public class UserAlreadyRegisteredException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyRegisteredException(Long id) {
        super(String.format("User with id %s is already registered in the system.", id));
    }
}
