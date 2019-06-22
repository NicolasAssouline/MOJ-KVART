package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.CouncilorRepository;
import hr.fer.opp.mojkvart.dao.ResidentRepository;
import hr.fer.opp.mojkvart.dao.UserRepository;
import hr.fer.opp.mojkvart.domain.Councilor;
import hr.fer.opp.mojkvart.service.CouncilorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.*;

@Service
public class CouncilorServiceJpa implements CouncilorService {

    @Autowired
    private CouncilorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Councilor> listAll() {
        return repository.findAll();
    }

    @Override
    public Councilor add(Councilor councilor) {
        checkUsernameTaken(councilor, userRepository);
        checkEmailTaken(councilor, residentRepository);
        checkAddResident(councilor, passwordEncoder);
        return repository.save(councilor);
    }

    @Override
    public Councilor fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Councilor with id " + id + " does not exist."));
    }

    @Override
    public Optional<Councilor> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
