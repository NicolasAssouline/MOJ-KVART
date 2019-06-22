package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.SuggestedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestedEventRepository extends JpaRepository<SuggestedEvent, Integer> {
}