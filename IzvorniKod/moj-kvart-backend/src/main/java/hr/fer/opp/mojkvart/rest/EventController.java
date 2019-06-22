package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.AcceptedEvent;
import hr.fer.opp.mojkvart.domain.Event;
import hr.fer.opp.mojkvart.domain.Moderator;
import hr.fer.opp.mojkvart.domain.SuggestedEvent;
import hr.fer.opp.mojkvart.rest.dto.in.EventIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.AcceptedEventDTO;
import hr.fer.opp.mojkvart.rest.dto.out.SuggestedEventDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.AcceptedEventService;
import hr.fer.opp.mojkvart.service.ModeratorService;
import hr.fer.opp.mojkvart.service.ResidentService;
import hr.fer.opp.mojkvart.service.SuggestedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static hr.fer.opp.mojkvart.rest.util.ControllerUtil.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private SuggestedEventService suggestedEventService;

    @Autowired
    private AcceptedEventService acceptedEventService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private ModeratorService moderatorService;

    @GetMapping("/suggested")
    @Secured("ROLE_MODERATOR")
    public List<SuggestedEventDTO> getSuggestedEvents() {
        return loggedInModerator(moderatorService).getNeighborhood()
                .getSuggestedEvents().stream()
                .map(ControllerUtil::toSuggestedEventDTO).collect(Collectors.toList());
    }

    @GetMapping("/suggested/{id}")
    @Secured("ROLE_MODERATOR")
    public ResponseEntity<SuggestedEventDTO> getSuggestedEvent(@PathVariable Integer id) {
        SuggestedEvent event = suggestedEventService.fetch(id);
        return loggedInModerator(moderatorService).getNeighborhood().getId()
                .equals(event.getNeighborhood().getId())
                ? ResponseEntity.ok(toSuggestedEventDTO(event))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/suggested")
    @Secured("ROLE_RESIDENT")
    public ResponseEntity<SuggestedEventDTO> suggestEvent(@RequestBody Event event) {
        SuggestedEvent suggestedEvent = suggestedEventService.add(
                toSuggestedEvent(event, loggedInResident(residentService)));
        return ResponseEntity.created(URI.create("/suggested/" + suggestedEvent.getId()))
                .body(toSuggestedEventDTO(suggestedEvent));
    }

    @PostMapping("/suggested/{id}")
    @Secured("ROLE_MODERATOR")
    public ResponseEntity<AcceptedEventDTO> acceptSuggestedEvent(@PathVariable Integer id) {
        SuggestedEvent suggestedEvent = suggestedEventService.fetch(id);
        Moderator moderator = loggedInModerator(moderatorService);
        return acceptEvent(id, suggestedEvent, moderator, toAcceptedEvent(suggestedEvent, moderator));
    }

    @PostMapping("/suggested/{id}/modify")
    @Secured("ROLE_MODERATOR")
    public ResponseEntity<AcceptedEventDTO> acceptSuggestedEvent(
            @PathVariable Integer id, @RequestBody EventIDTO dto) {
        Moderator moderator = loggedInModerator(moderatorService);
        SuggestedEvent suggestedEvent = suggestedEventService.fetch(id);
        return acceptEvent(id, suggestedEvent, moderator, toAcceptedEvent(dto, moderator));
    }

    @DeleteMapping("/suggested/{id}")
    @Secured("ROLE_MODERATOR")
    public ResponseEntity<Void> denySuggestedEvent(@PathVariable Integer id) {
        SuggestedEvent suggestedEvent = suggestedEventService.fetch(id);
        if (loggedInModerator(moderatorService).getNeighborhood().getId()
                .equals(suggestedEvent.getNeighborhood().getId())) {
            suggestedEventService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/accepted")
    @Secured("ROLE_RESIDENT")
    public List<AcceptedEventDTO> getAcceptedEvents() {
        return loggedInResident(residentService).getNeighborhood()
                .getAcceptedEvents().stream()
                .map(ControllerUtil::toAcceptedEventDTO).collect(Collectors.toList());
    }

    @GetMapping("/accepted/{id}")
    @Secured("ROLE_RESIDENT")
    public ResponseEntity<AcceptedEventDTO> getAcceptedEvents(@PathVariable Integer id) {
        AcceptedEvent event = acceptedEventService.fetch(id);
        return loggedInResident(residentService).getNeighborhood().getId()
                .equals(event.getNeighborhood().getId())
                ? ResponseEntity.ok(toAcceptedEventDTO(event))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private ResponseEntity<AcceptedEventDTO> acceptEvent(@PathVariable Integer id,
            SuggestedEvent suggestedEvent, Moderator moderator, AcceptedEvent acceptedEvent) {
        if (moderator.getNeighborhood().getId()
                .equals(suggestedEvent.getNeighborhood().getId())) {
            AcceptedEvent event = acceptedEventService.add(acceptedEvent);
            suggestedEventService.delete(id);
            return ResponseEntity.created(URI.create("/accepted/" + event.getId()))
                    .body(toAcceptedEventDTO(event));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
