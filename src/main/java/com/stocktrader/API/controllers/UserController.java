package com.stocktrader.API.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stocktrader.API.exceptions.UserAlreadyRegisteredException;
import com.stocktrader.API.exceptions.UserNotFoundException;
import com.stocktrader.API.models.User;
import com.stocktrader.API.services.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) throws UserNotFoundException {
			return ResponseEntity.ok(userService.findById(id));
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> insert(@RequestBody User user) throws UserAlreadyRegisteredException {
		userService.insert(user);
		return ResponseEntity.ok(user);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) throws UserNotFoundException {
		userService.update(id,user);
		return ResponseEntity.ok(user);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable Long id) throws UserNotFoundException {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
