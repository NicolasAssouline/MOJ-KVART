package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.UserRepository;
import hr.fer.opp.mojkvart.domain.User;
import hr.fer.opp.mojkvart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceJpa implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> listAll() {
        return repository.findAll();
    }

    @Override
    public User fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "User with ID " + id + " does not exist.")
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User add(User user) {
        Assert.isNull(user.getId(), "User ID must be null.");
        return repository.save(user);
    }
}
