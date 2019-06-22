package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.EventRepository;
import hr.fer.opp.mojkvart.domain.Event;
import hr.fer.opp.mojkvart.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceJpa implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public List<Event> listAll() {
        return repository.findAll();
    }

    @Override
    public Event add(Event event) {
        return repository.save(event);
    }
}
