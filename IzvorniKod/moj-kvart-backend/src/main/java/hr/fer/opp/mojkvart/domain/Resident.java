package hr.fer.opp.mojkvart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id")
public class Resident extends User {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private Street street;

    @JsonIgnore
    @OneToMany(mappedBy = "suggestedBy", cascade = CascadeType.REMOVE)
    private List<SuggestedEvent> suggestedEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "openedBy", cascade = CascadeType.REMOVE)
    private List<ForumThread> openedThreads;

    @JsonIgnore
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.REMOVE)
    private List<ForumPost> postedPosts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Neighborhood getNeighborhood() { return street.getNeighborhood(); }

    public List<SuggestedEvent> getSuggestedEvents() {
        return suggestedEvents;
    }

    public void setSuggestedEvents(List<SuggestedEvent> suggestedEvents) {
        this.suggestedEvents = suggestedEvents;
    }

    public List<ForumThread> getOpenedThreads() {
        return openedThreads;
    }

    public void setOpenedThreads(List<ForumThread> openedThreads) {
        this.openedThreads = openedThreads;
    }

    public List<ForumPost> getPostedPosts() {
        return postedPosts;
    }

    public void setPostedPosts(List<ForumPost> postedPosts) {
        this.postedPosts = postedPosts;
    }
}
