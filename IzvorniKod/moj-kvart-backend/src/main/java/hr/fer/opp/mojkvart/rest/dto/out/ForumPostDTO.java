package hr.fer.opp.mojkvart.rest.dto.out;

import java.time.LocalDateTime;

public class ForumPostDTO {

    private Integer id;
    private String content;
    private LocalDateTime postedOn;
    private ResidentDTO postedBy;

    public ForumPostDTO(Integer id, String content, LocalDateTime postedOn, ResidentDTO postedBy) {
        this.id = id;
        this.content = content;
        this.postedOn = postedOn;
        this.postedBy = postedBy;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public ResidentDTO getPostedBy() {
        return postedBy;
    }
}
