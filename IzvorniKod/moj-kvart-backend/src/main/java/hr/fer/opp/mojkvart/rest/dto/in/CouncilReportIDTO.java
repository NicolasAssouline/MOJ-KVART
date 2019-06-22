package hr.fer.opp.mojkvart.rest.dto.in;

import java.time.LocalDateTime;

public class CouncilReportIDTO {

    private String content;

    private LocalDateTime heldOn;

    public String getContent(){ return content; }

    public LocalDateTime getHeldOn(){ return heldOn; }
}
