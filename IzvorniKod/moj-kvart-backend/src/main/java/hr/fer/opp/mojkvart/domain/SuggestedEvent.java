package hr.fer.opp.mojkvart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class SuggestedEvent extends Event {

    @Column(nullable = false)
    private LocalDateTime suggestedOn;

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident suggestedBy;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;

    public LocalDateTime getSuggestedOn() {
        return suggestedOn;
    }

    public void setSuggestedOn(LocalDateTime suggestedOn) {
        this.suggestedOn = suggestedOn;
    }

    public Resident getSuggestedBy() {
        return suggestedBy;
    }

    public void setSuggestedBy(Resident suggestedBy) {
        this.suggestedBy = suggestedBy;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }
}
