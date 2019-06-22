package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Optional<Resident> findByUsername(String username);
    Optional<Resident> findByEmail(String email);
}