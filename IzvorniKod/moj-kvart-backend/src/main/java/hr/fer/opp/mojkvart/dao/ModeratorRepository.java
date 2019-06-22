package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
    Optional<Moderator> findByUsername(String username);
}