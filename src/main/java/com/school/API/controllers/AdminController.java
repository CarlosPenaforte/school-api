package com.school.API.controllers;

import com.school.API.exceptions.AdminAlreadyRegisteredException;
import com.school.API.models.Admin;
import com.school.API.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) throws AdminAlreadyRegisteredException {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        adminService.insert(admin);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/signup")
    public ResponseEntity<Admin> signup(@RequestBody Admin admin) throws AdminAlreadyRegisteredException {
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        adminService.insert(admin);
        return ResponseEntity.ok(admin);
    }
}
