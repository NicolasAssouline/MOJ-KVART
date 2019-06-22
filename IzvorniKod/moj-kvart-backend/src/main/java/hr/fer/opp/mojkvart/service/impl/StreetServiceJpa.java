package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.StreetRepository;
import hr.fer.opp.mojkvart.domain.Street;
import hr.fer.opp.mojkvart.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.NAME;

@Service
public class StreetServiceJpa implements StreetService {

    @Autowired
    private StreetRepository repository;

    @Override
    public List<Street> listAll() {
        return repository.findAll();
    }

    @Override
    public Street add(Street street) {
        checkAdd(street);
        return repository.save(street);
    }

    @Override
    public Optional<Street> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(Integer id) {
        checkExists(id);
        repository.deleteById(id);
    }

    private void checkAdd(Street street) {
        Assert.isNull(street.getId(), "Street ID must be null.");

        String name = street.getName();
        Assert.notNull(name, "Name is required.");
        Assert.isTrue(NAME.matcher(name).matches(),
                "Street name contains invalid characters.");

        Assert.notNull(street.getNeighborhood(), "Neighborhood is required.");
    }

    private void checkExists(Integer id) {
        repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Street with id " + id + " does not exist."));
    }
}
