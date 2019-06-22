package hr.fer.opp.mojkvart.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CouncilReport {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime heldOn;

    @Column(nullable = false)
    private LocalDateTime publishedOn;

    @ManyToOne
    @JoinColumn(name = "councilor_id", nullable = false)
    private Councilor publishedBy;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;

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

    public LocalDateTime getHeldOn() {
        return heldOn;
    }

    public void setHeldOn(LocalDateTime heldOn) {
        this.heldOn = heldOn;
    }

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Councilor getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(Councilor publishedBy) {
        this.publishedBy = publishedBy;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }
}
