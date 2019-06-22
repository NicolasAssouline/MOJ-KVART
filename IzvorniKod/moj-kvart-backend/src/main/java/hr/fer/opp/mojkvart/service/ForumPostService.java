package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.ForumPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ForumPostService {
	
	List<ForumPost> listAll();
	
	ForumPost add(ForumPost forumPost);
	
	ForumPost modify(ForumPost forumPost);
	
	ForumPost fetch(Integer id);

	void delete(Integer id);
}
