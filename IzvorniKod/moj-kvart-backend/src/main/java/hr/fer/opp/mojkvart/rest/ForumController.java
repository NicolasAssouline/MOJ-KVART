package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.ForumPost;
import hr.fer.opp.mojkvart.domain.ForumThread;
import hr.fer.opp.mojkvart.rest.dto.out.ForumPostDTO;
import hr.fer.opp.mojkvart.rest.dto.out.ForumThreadDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.ForumPostService;
import hr.fer.opp.mojkvart.service.ForumThreadService;
import hr.fer.opp.mojkvart.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static hr.fer.opp.mojkvart.rest.util.ControllerUtil.*;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ResidentService residentService;

    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private ForumPostService forumPostService;

    @GetMapping("/threads")
    @Secured("ROLE_RESIDENT")
    public List<ForumThreadDTO> currentForumThreads() {
        return loggedInResident(residentService).getNeighborhood()
                .getForumThreads().stream()
                .map(ControllerUtil::toForumThreadDTO).collect(Collectors.toList());
    }

    @PostMapping("/threads")
    @Secured("ROLE_RESIDENT")
    public ResponseEntity<ForumThreadDTO> newForumThread(@RequestBody String title) {
        ForumThread thread = forumThreadService.add(
                toForumThread(title, loggedInResident(residentService)));
        return ResponseEntity.created(URI.create("/forum/" + thread.getId()))
                .body(toForumThreadDTO(thread));
    }

    @PutMapping("/threads/{id}")
    @Secured("ROLE_MODERATOR")
    public ResponseEntity<ForumThreadDTO> modifyForumThread(@PathVariable Integer id,
            @RequestBody String title) {
        ForumThread thread = forumThreadService.fetch(id);
        thread.setTitle(title);
        return loggedInResident(residentService).getNeighborhood().getId()
                .equals(thread.getNeighborhood().getId())
                ? ResponseEntity.ok(toForumThreadDTO(forumThreadService.modify(thread)))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/threads/{id}")
    @Secured("ROLE_MODERATOR")
    public ResponseEntity<Void> removeForumThread(@PathVariable Integer id) {
        ForumThread thread = forumThreadService.fetch(id);
        if (loggedInResident(residentService).getNeighborhood().getId()
                .equals(thread.getNeighborhood().getId())) {
            forumThreadService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/threads/{id}")
    @Secured("ROLE_RESIDENT")
    public ResponseEntity<List<ForumPostDTO>> getForumPosts(@PathVariable Integer id) {
        ForumThread thread = forumThreadService.fetch(id);
        return loggedInResident(residentService).getNeighborhood().getId()
                .equals(thread.getNeighborhood().getId())
                ? ResponseEntity.ok(
                        thread.getPosts().stream()
                                .map(ControllerUtil::toForumPostDTO)
                                .collect(Collectors.toList()))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/threads/{id}")
    @Secured("ROLE_RESIDENT")
    public ResponseEntity<ForumPostDTO> newForumPost(@PathVariable Integer id,
            @RequestBody String content) {
        ForumThread thread = forumThreadService.fetch(id);
        return thread.getNeighborhood().getId()
                .equals(loggedInResident(residentService).getNeighborhood().getId())
                ? ResponseEntity.ok(toForumPostDTO(forumPostService.add(toForumPost(
                        content, thread, loggedInResident(residentService)))))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/posts/{id}")
    @Secured({"ROLE_RESIDENT", "ROLE_MODERATOR"})
    public ResponseEntity<ForumPostDTO> modifyForumPost(@PathVariable Integer id,
            @RequestBody String content, @AuthenticationPrincipal User user) {
        ForumPost post = forumPostService.fetch(id);
        post.setContent(content);
        if (user.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_MODERATOR"))) {
            return loggedInResident(residentService).getNeighborhood().getId()
                    .equals(post.getForumThread().getNeighborhood().getId())
                    ? ResponseEntity.ok(toForumPostDTO(forumPostService.modify(post)))
                    : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return loggedInResident(residentService).getId()
                    .equals(post.getPostedBy().getId())
                    ? ResponseEntity.ok(toForumPostDTO(forumPostService.modify(post)))
                    : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/posts/{id}")
    @Secured({"ROLE_RESIDENT", "ROLE_MODERATOR"})
    public ResponseEntity<Void> removeForumPost(@PathVariable Integer id,
            @AuthenticationPrincipal User user) {
        ForumPost post = forumPostService.fetch(id);
        if (user.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_MODERATOR"))
                && loggedInResident(residentService).getNeighborhood().getId()
                .equals(post.getForumThread().getNeighborhood().getId())) {
            forumPostService.delete(id);
            return ResponseEntity.ok().build();
        } else if (loggedInResident(residentService).getId()
                .equals(post.getPostedBy().getId())) {
            forumPostService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
