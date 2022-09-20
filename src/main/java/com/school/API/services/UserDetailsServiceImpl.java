package com.school.API.services;

import com.school.API.exceptions.UserAlreadyRegisteredException;
import com.school.API.exceptions.UserNotFoundException;
import com.school.API.models.User;
import com.school.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws UserNotFoundException {
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public User findByUsername(String username) throws UserNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public void insert(User user) throws UserAlreadyRegisteredException {
        String username = user.getUsername();
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isEmpty()) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyRegisteredException(username);
        }
    }

    public void update(User user) throws UserNotFoundException {
        String username = user.getUsername();
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            userRepository.save(user);
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
