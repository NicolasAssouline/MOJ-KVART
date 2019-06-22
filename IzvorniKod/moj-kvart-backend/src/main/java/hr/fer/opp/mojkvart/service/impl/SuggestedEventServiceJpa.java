package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.SuggestedEventRepository;
import hr.fer.opp.mojkvart.domain.SuggestedEvent;
import hr.fer.opp.mojkvart.service.SuggestedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.checkAddEvent;

@Service
public class SuggestedEventServiceJpa implements SuggestedEventService {

    @Autowired
    private SuggestedEventRepository repository;

    @Override
    public List<SuggestedEvent> listAll() {
        return repository.findAll();
    }

    @Override
    public SuggestedEvent add(SuggestedEvent suggestedEvent) {
        checkAdd(suggestedEvent);
        return repository.save(suggestedEvent);
    }

    @Override
    public SuggestedEvent fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Suggested event with ID " + id + " does not exist."));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void checkAdd(SuggestedEvent suggestedEvent) {
        checkAddEvent(suggestedEvent);
        Assert.notNull(suggestedEvent.getSuggestedBy(), "Suggested by field is required.");
        Assert.notNull(suggestedEvent.getNeighborhood(), "Neighborhood is required.");

        suggestedEvent.setSuggestedOn(LocalDateTime.now());
    }
}
