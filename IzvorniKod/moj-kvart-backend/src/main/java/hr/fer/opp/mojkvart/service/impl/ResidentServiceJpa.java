package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.ResidentRepository;
import hr.fer.opp.mojkvart.dao.UserRepository;
import hr.fer.opp.mojkvart.domain.Resident;
import hr.fer.opp.mojkvart.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.*;

@Service
public class ResidentServiceJpa implements ResidentService {

    @Autowired
    private ResidentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Resident> listAll() {
        return repository.findAll();
    }

    @Override
    public Resident add(Resident resident) {
        checkUsernameTaken(resident, userRepository);
        checkEmailTaken(resident, repository);
        checkAddResident(resident, passwordEncoder);
        return repository.save(resident);
    }

    @Override
    public Resident modify(Resident resident) {
        checkUsernameTaken(resident, userRepository);
        checkEmailTaken(resident, repository);
        checkModifyResident(resident, passwordEncoder);
        return repository.save(resident);
    }

    @Override
    public Resident fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Resident with id " + id + " does not exist."));
    }

    @Override
    public Optional<Resident> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<Resident> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
