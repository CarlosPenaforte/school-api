package com.stocktrader.API.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stocktrader.API.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByCpf(String cpf);
}
