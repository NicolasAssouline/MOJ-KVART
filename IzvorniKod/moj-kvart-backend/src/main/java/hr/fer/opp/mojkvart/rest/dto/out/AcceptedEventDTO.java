package hr.fer.opp.mojkvart.rest.dto.out;

import java.time.LocalDateTime;

public class AcceptedEventDTO {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime publishedOn;

    public AcceptedEventDTO(Integer id, String title, String description, String location,
            LocalDateTime start, LocalDateTime end, LocalDateTime publishedOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.publishedOn = publishedOn;
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

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }
}
