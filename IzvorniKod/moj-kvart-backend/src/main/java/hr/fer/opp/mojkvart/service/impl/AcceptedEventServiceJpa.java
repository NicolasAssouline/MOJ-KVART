package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.AcceptedEventRepository;
import hr.fer.opp.mojkvart.domain.AcceptedEvent;
import hr.fer.opp.mojkvart.service.AcceptedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.checkAddEvent;

@Service
public class AcceptedEventServiceJpa implements AcceptedEventService {

    @Autowired
    private AcceptedEventRepository repository;

    @Override
    public List<AcceptedEvent> listAll() {
        return repository.findAll();
    }

    @Override
    public AcceptedEvent add(AcceptedEvent acceptedEvent) {
        checkAdd(acceptedEvent);
        return repository.save(acceptedEvent);
    }

    @Override
    public AcceptedEvent fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Accepted event with ID " + id + " does not exist."));
    }

    private void checkAdd(AcceptedEvent acceptedEvent) {
        checkAddEvent(acceptedEvent);
        Assert.notNull(acceptedEvent.getNeighborhood(), "Neighborhood is required.");

        acceptedEvent.setPublishedOn(LocalDateTime.now());
    }
}
