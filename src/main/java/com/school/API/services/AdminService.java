package com.school.API.services;

import com.school.API.exceptions.AdminAlreadyRegisteredException;
import com.school.API.exceptions.AdminNotFoundException;
import com.school.API.models.Admin;
import com.school.API.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(Long id) throws AdminNotFoundException {
        Optional<Admin> optAdmin = adminRepository.findById(id);
        if (optAdmin.isEmpty()) {
            throw new AdminNotFoundException(id);
        }
        return optAdmin.get();
    }

    public void insert(Admin admin) throws AdminAlreadyRegisteredException {
        String username = admin.getUsername();
        Optional<Admin> newUser = adminRepository.findByUsername(username);
        if (newUser.isEmpty()) {
            adminRepository.save(admin);
        } else {
            throw new AdminAlreadyRegisteredException(newUser.get().getId());
        }
    }

    public void update(Long id, Admin admin) throws AdminNotFoundException {
        Optional<Admin> userDb = adminRepository.findById(id);
        if (userDb.isPresent()) {
            admin.setId(id);
            adminRepository.save(admin);
        } else {
            throw new AdminNotFoundException(id);
        }
    }

    public void delete(Long id) throws AdminNotFoundException {
        Optional<Admin> userDb = adminRepository.findById(id);
        if (userDb.isPresent()) {
            adminRepository.deleteById(id);
        } else {
            throw new AdminNotFoundException(id);
        }
    }
}
