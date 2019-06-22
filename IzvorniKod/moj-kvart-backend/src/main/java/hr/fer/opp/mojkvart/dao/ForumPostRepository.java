package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostRepository extends JpaRepository<ForumPost, Integer> {
}