package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.Councilor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouncilorRepository extends JpaRepository<Councilor, Integer> {
    Optional<Councilor> findByUsername(String username);
}