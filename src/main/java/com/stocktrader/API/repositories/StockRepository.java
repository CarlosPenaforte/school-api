package com.stocktrader.API.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stocktrader.API.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	
	Optional<Stock> findByName(String name);
	
	List<Stock> findAll();
}
