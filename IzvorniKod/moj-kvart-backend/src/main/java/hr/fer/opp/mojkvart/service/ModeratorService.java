package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.Moderator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ModeratorService {
	
	List<Moderator> listAll();
	
	Moderator add(Moderator moderator);
	
	Moderator fetch(Integer id);
	
	Optional<Moderator> findByUsername(String username);
}
