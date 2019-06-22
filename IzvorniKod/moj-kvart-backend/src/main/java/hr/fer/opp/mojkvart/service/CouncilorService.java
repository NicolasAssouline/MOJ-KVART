package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.CouncilReport;
import hr.fer.opp.mojkvart.domain.Councilor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CouncilorService {
	
	List<Councilor> listAll();
	
	Councilor add(Councilor councilor);
	
	Councilor fetch(Integer id);
	
	Optional<Councilor> findByUsername(String username);
}
