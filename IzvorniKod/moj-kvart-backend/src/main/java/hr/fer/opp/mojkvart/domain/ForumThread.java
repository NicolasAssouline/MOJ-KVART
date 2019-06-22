package hr.fer.opp.mojkvart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ForumThread {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime openedOn;

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident openedBy;

    @OneToMany(mappedBy = "forumThread", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ForumPost> posts;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(LocalDateTime openedOn) {
        this.openedOn = openedOn;
    }

    public Resident getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(Resident openedBy) {
        this.openedBy = openedBy;
    }

    public List<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ForumPost> posts) {
        this.posts = posts;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }
}
