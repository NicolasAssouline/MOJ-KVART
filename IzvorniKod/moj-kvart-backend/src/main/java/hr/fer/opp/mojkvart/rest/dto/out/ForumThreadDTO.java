package hr.fer.opp.mojkvart.rest.dto.out;

import java.time.LocalDateTime;

public class ForumThreadDTO {

    private Integer id;
    private String title;
    private LocalDateTime openedOn;
    private ResidentDTO openedBy;

    public ForumThreadDTO(Integer id, String title, LocalDateTime openedOn, ResidentDTO openedBy) {
        this.id = id;
        this.title = title;
        this.openedOn = openedOn;
        this.openedBy = openedBy;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getOpenedOn() {
        return openedOn;
    }

    public ResidentDTO getOpenedBy() {
        return openedBy;
    }
}
