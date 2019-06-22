package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.Administrator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministratorService {
	
	List<Administrator> listAll();
	
	Administrator add(Administrator administrator);
	
	Administrator fetch(Integer id);
}
