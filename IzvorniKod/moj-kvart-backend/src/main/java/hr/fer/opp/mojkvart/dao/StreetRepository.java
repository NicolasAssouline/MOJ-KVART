package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Integer> {
    Optional<Street> findByName(String name);
}