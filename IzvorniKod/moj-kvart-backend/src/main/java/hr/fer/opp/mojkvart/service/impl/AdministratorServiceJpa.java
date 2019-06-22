package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.AdministratorRepository;
import hr.fer.opp.mojkvart.dao.UserRepository;
import hr.fer.opp.mojkvart.domain.Administrator;
import hr.fer.opp.mojkvart.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.checkAddUser;
import static hr.fer.opp.mojkvart.service.util.ServiceUtil.checkUsernameTaken;

@Service
public class AdministratorServiceJpa implements AdministratorService {

    @Autowired
    private AdministratorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Administrator> listAll() {
        return repository.findAll();
    }

    @Override
    public Administrator add(Administrator administrator) {
        checkUsernameTaken(administrator, userRepository);
        checkAddUser(administrator, passwordEncoder);
        return repository.save(administrator);
    }

    @Override
    public Administrator fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Administrator with ID " + id + " does not exist."));
    }
}
