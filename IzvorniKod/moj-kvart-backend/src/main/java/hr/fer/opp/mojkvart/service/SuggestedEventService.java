package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.SuggestedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SuggestedEventService {

	List<SuggestedEvent> listAll();
	
	SuggestedEvent add(SuggestedEvent suggestedEvent);
	
	SuggestedEvent fetch(Integer id);
	
	void delete(Integer id);
}
