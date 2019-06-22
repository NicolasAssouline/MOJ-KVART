package hr.fer.opp.mojkvart.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ForumPost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime postedOn;

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident postedBy;

    @ManyToOne
    @JoinColumn(name = "forum_thread_id", nullable = false)
    private ForumThread forumThread;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public Resident getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Resident postedBy) {
        this.postedBy = postedBy;
    }

    public ForumThread getForumThread() {
        return forumThread;
    }

    public void setForumThread(ForumThread forumThread) {
        this.forumThread = forumThread;
    }
}
