package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Integer> {
}