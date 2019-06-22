package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {
    Optional<Neighborhood> findByName(String name);
}