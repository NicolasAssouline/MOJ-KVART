package hr.fer.opp.mojkvart.rest.dto.in;

import java.time.LocalDateTime;

public class EventIDTO {

    private String title;

    private String description;

    private String location;

    private LocalDateTime start;

    private LocalDateTime end;

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
}
