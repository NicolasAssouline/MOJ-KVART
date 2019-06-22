package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.ForumThread;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ForumThreadService {
	
	List<ForumThread> listAll();
	
	ForumThread add(ForumThread forumThread);
	
	ForumThread modify(ForumThread forumThread);
	
	ForumThread fetch(Integer id);

	void delete(Integer id);
}
