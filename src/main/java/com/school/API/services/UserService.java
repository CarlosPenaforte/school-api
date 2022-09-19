package com.school.API.services;

import com.school.API.exceptions.UserAlreadyRegisteredException;
import com.school.API.exceptions.UserNotFoundException;
import com.school.API.models.Role;
import com.school.API.models.User;
import com.school.API.repositories.RoleRepository;
import com.school.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("ADMIN [ " + username + " ] not found");
        }
        User user = optionalUser.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws UserNotFoundException {
        Optional<User> optAdmin = userRepository.findById(id);
        if (optAdmin.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return optAdmin.get();
    }

    public void insert(User user) throws UserAlreadyRegisteredException {
        String username = user.getUsername();
        Optional<User> newUser = userRepository.findByUsername(username);
        if (newUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().forEach(role -> roleRepository.save(role));
            userRepository.save(user);
        } else {
            throw new UserAlreadyRegisteredException(newUser.get().getId());
        }
    }

    public void update(Long id, User user) throws UserNotFoundException {
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().forEach(role -> roleRepository.save(role));
            userRepository.save(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
