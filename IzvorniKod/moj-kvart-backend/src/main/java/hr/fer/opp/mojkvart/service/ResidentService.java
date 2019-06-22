package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.Resident;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ResidentService {
	
	List<Resident> listAll();
	
	Resident add(Resident resident);
	
	Resident modify(Resident resident);
	
	Resident fetch(Integer id);
	
	Optional<Resident> findByUsername(String username);
	
	Optional<Resident> findByEmail(String email);
}
