package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.ForumThreadRepository;
import hr.fer.opp.mojkvart.domain.ForumThread;
import hr.fer.opp.mojkvart.service.ForumThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static hr.fer.opp.mojkvart.service.util.ServiceUtil.NAME;

@Service
public class ForumThreadServiceJpa implements ForumThreadService {

    @Autowired
    private ForumThreadRepository repository;

    @Override
    public List<ForumThread> listAll() {
        return repository.findAll();
    }

    @Override
    public ForumThread add(ForumThread forumThread) {
        checkAdd(forumThread);
        return repository.save(forumThread);
    }

    @Override
    public ForumThread modify(ForumThread forumThread) {
        checkModify(forumThread);
        return repository.save(forumThread);
    }

    @Override
    public ForumThread fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Forum thread with ID " + id + " does not exist."));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void checkAdd(ForumThread forumThread) {
        Assert.isNull(forumThread.getId(), "Forum thread ID must be null.");
        checkModify(forumThread);
        forumThread.setOpenedOn(LocalDateTime.now());
    }

    private void checkModify(ForumThread forumThread) {
        String title = forumThread.getTitle();
        Assert.hasLength(title, "Title is required.");
        Assert.isTrue(NAME.matcher(title).matches(),
                "Title contains invalid characters.");

        Assert.notNull(forumThread.getOpenedBy(), "Thread author is required.");
        Assert.notNull(forumThread.getNeighborhood(), "Neighborhood is required.");
    }
}
