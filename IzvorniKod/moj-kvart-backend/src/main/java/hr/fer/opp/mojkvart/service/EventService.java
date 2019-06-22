package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
	
	List<Event> listAll();
	
	Event add(Event event);
}
