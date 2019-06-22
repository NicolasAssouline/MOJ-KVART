package hr.fer.opp.mojkvart.rest.dto.out;

import java.time.LocalDateTime;

public class CouncilReportDTO {

    private Integer id;
    private String content;
    private LocalDateTime heldOn;
    private LocalDateTime publishedOn;
    private ResidentDTO publishedBy;

    public CouncilReportDTO(Integer id, String content, LocalDateTime heldOn, LocalDateTime publishedOn, ResidentDTO publishedBy) {
        this.id = id;
        this.content = content;
        this.heldOn = heldOn;
        this.publishedOn = publishedOn;
        this.publishedBy = publishedBy;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getHeldOn() {
        return heldOn;
    }

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public ResidentDTO getPublishedBy() {
        return publishedBy;
    }
}
