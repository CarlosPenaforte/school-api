package com.school.API.services;

import com.school.API.data.AdminDetailsData;
import com.school.API.models.Admin;
import com.school.API.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if(admin.isEmpty()){
            throw new UsernameNotFoundException("ADMIN [ "+username+" ] not found");
        }

        return new AdminDetailsData();
    }
}
