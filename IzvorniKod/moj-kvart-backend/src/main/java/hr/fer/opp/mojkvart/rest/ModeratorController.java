package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Moderator;
import hr.fer.opp.mojkvart.rest.dto.in.ResidentIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.ResidentDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.ModeratorService;
import hr.fer.opp.mojkvart.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static hr.fer.opp.mojkvart.rest.util.ControllerUtil.toResidentDTO;

@RestController
@RequestMapping("/users/moderators")
@Secured("ROLE_ADMIN")
public class ModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private StreetService streetService;

    @GetMapping("")
    public List<ResidentDTO> getModerators() {
        return moderatorService.listAll().stream()
                .map(ControllerUtil::toResidentDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResidentDTO getModerator(@PathVariable Integer id) {
        return toResidentDTO(moderatorService.fetch(id));
    }

    @PostMapping("")
    public ResponseEntity<ResidentDTO> createModerator(
            @RequestBody ResidentIDTO dto) {
        ResidentDTO moderator = toResidentDTO(moderatorService.add(toModerator(dto)));
        return ResponseEntity.created(URI.create(
                "/users/moderators/" + moderator.getId())).body(moderator);
    }

    private Moderator toModerator(ResidentIDTO dto) {
        Moderator moderator = new Moderator();
        ControllerUtil.toResident(dto, moderator, streetService);
        return moderator;
    }
}
