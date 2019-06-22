package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.Street;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StreetService {
	
	List<Street> listAll();
	
	Street add(Street street);
	
	Optional<Street> findByName(String name);

	void delete(Integer id);
}
