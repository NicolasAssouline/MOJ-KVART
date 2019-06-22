package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
	
	List<User> listAll();

	User add(User user);

	User fetch(Integer id);

	Optional<User> findByUsername(String username);
}
