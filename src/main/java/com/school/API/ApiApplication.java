package com.school.API;

import com.school.API.models.User;
import com.school.API.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    CommandLineRunner run(UserService userService){
        return args -> {
            User user = new User("admin","admin123","administrator", new HashSet<>());
            userService.insert(user);
        };
    }

}
