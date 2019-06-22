package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.Neighborhood;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NeighborhoodService {
	
	List<Neighborhood> listAll();
	
	Neighborhood add(Neighborhood neighborhood);
	
	Optional<Neighborhood> findByName(String name);

	void delete(Integer id);
}
