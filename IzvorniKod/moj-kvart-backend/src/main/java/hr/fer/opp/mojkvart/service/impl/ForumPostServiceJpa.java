package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.ForumPostRepository;
import hr.fer.opp.mojkvart.domain.ForumPost;
import hr.fer.opp.mojkvart.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForumPostServiceJpa implements ForumPostService {

    @Autowired
    private ForumPostRepository repository;

    @Override
    public List<ForumPost> listAll() {
        return repository.findAll();
    }

    @Override
    public ForumPost add(ForumPost forumPost) {
        checkAdd(forumPost);
        return repository.save(forumPost);
    }

    @Override
    public ForumPost modify(ForumPost forumPost) {
        checkModify(forumPost);
        return repository.save(forumPost);
    }

    @Override
    public ForumPost fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Forum post with ID " + id + " does not exist."));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void checkAdd(ForumPost forumPost) {
        Assert.isNull(forumPost.getId(), "Forum post ID must be null.");
        checkModify(forumPost);
        forumPost.setPostedOn(LocalDateTime.now());
    }

    private void checkModify(ForumPost forumPost) {
        Assert.hasLength(forumPost.getContent(), "Content is required.");
        Assert.notNull(forumPost.getForumThread(), "Forum thread is required.");
        Assert.notNull(forumPost.getPostedBy(), "Forum post author is required.");
    }
}
