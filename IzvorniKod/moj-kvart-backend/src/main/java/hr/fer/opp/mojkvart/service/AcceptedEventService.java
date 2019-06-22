package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.AcceptedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcceptedEventService {
	
	List<AcceptedEvent> listAll();
	
	AcceptedEvent add(AcceptedEvent acceptedEvent);
	
	AcceptedEvent fetch(Integer id);
}
