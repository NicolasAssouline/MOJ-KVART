package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
