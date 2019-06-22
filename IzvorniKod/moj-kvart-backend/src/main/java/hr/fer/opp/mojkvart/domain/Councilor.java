package hr.fer.opp.mojkvart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Councilor extends Resident {

    @JsonIgnore
    @OneToMany(mappedBy = "publishedBy", cascade = CascadeType.REMOVE)
    private List<CouncilReport> publishedReports;

    public List<CouncilReport> getPublishedReports() {
        return publishedReports;
    }

    public void setPublishedReports(List<CouncilReport> publishedReports) {
        this.publishedReports = publishedReports;
    }
}
