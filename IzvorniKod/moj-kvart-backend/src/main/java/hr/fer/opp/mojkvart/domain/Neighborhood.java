package hr.fer.opp.mojkvart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Neighborhood {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.REMOVE)
    private List<ForumThread> forumThreads;

    @JsonIgnore
    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.REMOVE)
    private List<AcceptedEvent> acceptedEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.REMOVE)
    private List<SuggestedEvent> suggestedEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.REMOVE)
    private List<CouncilReport> councilReports;

    @JsonIgnore
    @OneToMany(mappedBy = "neighborhood", cascade = CascadeType.REMOVE)
    private List<Street> streets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public void setForumThreads(List<ForumThread> forumThreads) {
        this.forumThreads = forumThreads;
    }

    public List<AcceptedEvent> getAcceptedEvents() {
        return acceptedEvents;
    }

    public void setAcceptedEvents(List<AcceptedEvent> acceptedEvents) {
        this.acceptedEvents = acceptedEvents;
    }

    public List<SuggestedEvent> getSuggestedEvents() {
        return suggestedEvents;
    }

    public void setSuggestedEvents(List<SuggestedEvent> suggestedEvents) {
        this.suggestedEvents = suggestedEvents;
    }

    public List<CouncilReport> getCouncilReports() {
        return councilReports;
    }

    public void setCouncilReports(List<CouncilReport> councilReports) {
        this.councilReports = councilReports;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }
}
