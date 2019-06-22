package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.AcceptedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptedEventRepository extends JpaRepository<AcceptedEvent, Integer> {
}