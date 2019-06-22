package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.ModeratorRepository;
import hr.fer.opp.mojkvart.dao.ResidentRepository;
import hr.fer.opp.mojkvart.dao.UserRepository;
import hr.fer.opp.mojkvart.domain.Moderator;
import hr.fer.opp.mojkvart.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.*;

@Service
public class ModeratorServiceJpa implements ModeratorService {

    @Autowired
    private ModeratorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Moderator> listAll() {
        return repository.findAll();
    }

    @Override
    public Moderator add(Moderator moderator) {
        checkUsernameTaken(moderator, userRepository);
        checkEmailTaken(moderator, residentRepository);
        checkAddResident(moderator, passwordEncoder);
        return repository.save(moderator);
    }

    @Override
    public Moderator fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                        "Moderator with id " + id + " does not exist."));
    }

    @Override
    public Optional<Moderator> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
