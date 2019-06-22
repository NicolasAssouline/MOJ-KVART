package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.NeighborhoodRepository;
import hr.fer.opp.mojkvart.domain.Neighborhood;
import hr.fer.opp.mojkvart.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.NAME;

@Service
public class NeighborhoodServiceJpa implements NeighborhoodService {

    @Autowired
    private NeighborhoodRepository repository;

    @Override
    public List<Neighborhood> listAll() {
        return repository.findAll();
    }

    @Override
    public Neighborhood add(Neighborhood neighborhood) {
        checkAdd(neighborhood);
        return repository.save(neighborhood);
    }

    @Override
    public Optional<Neighborhood> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(Integer id) {
        checkExists(id);
        repository.deleteById(id);
    }

    private void checkAdd(Neighborhood neighborhood) {
        Assert.isNull(neighborhood.getId(), "Neighborhood ID must be null.");
        Assert.isTrue(NAME.matcher(neighborhood.getName()).matches(),
                "Neighborhood name contains invalid characters.");
    }

    private void checkExists(Integer id) {
        repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Hood with id " + id + " does not exist."));
    }
}
