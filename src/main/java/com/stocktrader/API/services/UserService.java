package com.stocktrader.API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktrader.API.exceptions.UserAlreadyRegisteredException;
import com.stocktrader.API.exceptions.UserNotFoundException;
import com.stocktrader.API.models.Stock;
import com.stocktrader.API.models.User;
import com.stocktrader.API.repositories.StockRepository;
import com.stocktrader.API.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StockRepository stockRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) throws UserNotFoundException {
		if (userRepository.findById(id).isEmpty())
			throw new UserNotFoundException(id);
		return userRepository.findById(id).get();
	}

	public void insert(User user) throws UserAlreadyRegisteredException {
		String cpf = user.getCpf();
		Optional<User> newUser = userRepository.findByCpf(cpf);
		if (newUser.isEmpty()) {
			if (!user.getStockList().isEmpty()) {
				for (Stock stock : user.getStockList()) {
					Optional<Stock> stockDb = stockRepository.findByName(stock.getName());
					if (stockDb.isEmpty()) {
						stock.setOwnerCpf(cpf);
						stockRepository.save(stock);
					}
				}
			}
			
			userRepository.save(user);
		} else throw new UserAlreadyRegisteredException(newUser.get().getId());
	}

	public void update(Long id, User user) throws UserNotFoundException {
		Optional<User> userDb = userRepository.findById(id);
		if (userDb.isPresent()) {
			if (!user.getStockList().isEmpty()) {
				for (Stock stock : user.getStockList()) {
					stock.setOwnerCpf(user.getCpf());
					stockRepository.save(stock);
				}
			}
			user.setId(id);
			userRepository.save(user);
		} else throw new UserNotFoundException(id);
	}

	public void delete(Long id) throws UserNotFoundException {
		Optional<User> userDb = userRepository.findById(id);
		if (userDb.isPresent()) {
			userRepository.deleteById(id);
		} else throw new UserNotFoundException(id);
	}
}
