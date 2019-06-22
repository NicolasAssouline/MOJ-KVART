package hr.fer.opp.mojkvart.rest.dto.out;

import java.time.LocalDateTime;

public class SuggestedEventDTO {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime suggestedOn;
    private ResidentDTO suggestedBy;

    public SuggestedEventDTO(Integer id, String title, String description,
            String location, LocalDateTime start, LocalDateTime end, LocalDateTime suggestedOn, ResidentDTO suggestedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.suggestedOn = suggestedOn;
        this.suggestedBy = suggestedBy;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getSuggestedOn() {
        return suggestedOn;
    }

    public ResidentDTO getSuggestedBy() {
        return suggestedBy;
    }
}
