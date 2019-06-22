package hr.fer.opp.mojkvart.rest.util;

import hr.fer.opp.mojkvart.domain.*;
import hr.fer.opp.mojkvart.rest.dto.in.CouncilReportIDTO;
import hr.fer.opp.mojkvart.rest.dto.in.EventIDTO;
import hr.fer.opp.mojkvart.rest.dto.in.ResidentIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.*;
import hr.fer.opp.mojkvart.service.*;
import org.springframework.security.core.context.SecurityContextHolder;

public final class ControllerUtil {

    private ControllerUtil() {
    }

    public static User loggedInUser(UserService userService) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(name).orElseThrow(
                () -> new IllegalArgumentException("User " + name + " not found."));
    }

    public static Resident loggedInResident(ResidentService residentService) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return residentService.findByUsername(name).orElseThrow(
                () -> new IllegalArgumentException("Resident " + name + " not found."));
    }

    public static Moderator loggedInModerator(ModeratorService moderatorService) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return moderatorService.findByUsername(name).orElseThrow(
                () -> new IllegalArgumentException("Moderator " + name + " not found."));
    }

    public static Councilor loggedInCouncilor(CouncilorService councilorService) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return councilorService.findByUsername(name).orElseThrow(
                () -> new IllegalArgumentException("User " + name + " not found."));
    }

    public static void toResident(ResidentIDTO dto, Resident resident,
            StreetService streetService) {
        resident.setUsername(dto.getUsername());
        resident.setPasswordHash(dto.getPassword());
        resident.setFirstName(dto.getFirstName());
        resident.setLastName(dto.getLastName());

        String email = dto.getEmail();
        resident.setEmail(email);

        Street street = streetService.findByName(dto.getStreet())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Street " + dto.getStreet() + " does not exist."));
        resident.setStreet(street);
    }

    public static UserDTO toUserDTO(User user) {
        String role;
        if (user instanceof Administrator) role = "ADMIN";
        else if (user instanceof Moderator) role = "MODERATOR";
        else if (user instanceof Councilor) role = "COUNCILOR";
        else role = "RESIDENT";
        return new UserDTO(user.getId(), user.getUsername(), role);
    }

    public static ResidentDTO toResidentDTO(Resident resident) {
        String role;
        if (resident instanceof Moderator) role = "MODERATOR";
        else if (resident instanceof Councilor) role = "COUNCILOR";
        else role = "RESIDENT";
        return new ResidentDTO(
                resident.getId(), resident.getUsername(),
                resident.getCreatedOn(), resident.getFirstName(),
                resident.getLastName(), resident.getEmail(),
                resident.getStreet().getName(),
                role
        );
    }

    public static ForumThreadDTO toForumThreadDTO(ForumThread forumThread) {
        return new ForumThreadDTO(
                forumThread.getId(),
                forumThread.getTitle(),
                forumThread.getOpenedOn(),
                toResidentDTO(forumThread.getOpenedBy())
        );
    }

    public static ForumThread toForumThread(String title, Resident author) {
        ForumThread thread = new ForumThread();
        thread.setTitle(title);
        thread.setOpenedBy(author);
        thread.setNeighborhood(author.getStreet().getNeighborhood());
        return thread;
    }

    public static ForumPostDTO toForumPostDTO(ForumPost forumPost) {
        return new ForumPostDTO(
                forumPost.getId(),
                forumPost.getContent(),
                forumPost.getPostedOn(),
                toResidentDTO(forumPost.getPostedBy())
        );
    }

    public static ForumPost toForumPost(String content, ForumThread thread,
                                        Resident author) {
        ForumPost post = new ForumPost();
        post.setContent(content);
        post.setPostedBy(author);
        post.setForumThread(thread);
        return post;
    }

    public static SuggestedEventDTO toSuggestedEventDTO(SuggestedEvent event) {
        return new SuggestedEventDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getStart(),
                event.getEnd(),
                event.getSuggestedOn(),
                toResidentDTO(event.getSuggestedBy())
        );
    }

    public static SuggestedEvent toSuggestedEvent(Event event, Resident resident) {
        SuggestedEvent suggestedEvent = new SuggestedEvent();
        suggestedEvent.setTitle(event.getTitle());
        suggestedEvent.setDescription(event.getDescription());
        suggestedEvent.setLocation(event.getLocation());
        suggestedEvent.setStart(event.getStart());
        suggestedEvent.setEnd(event.getEnd());
        suggestedEvent.setSuggestedBy(resident);
        suggestedEvent.setNeighborhood(resident.getStreet().getNeighborhood());
        return suggestedEvent;
    }

    public static AcceptedEventDTO toAcceptedEventDTO(AcceptedEvent event) {
        return new AcceptedEventDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getStart(),
                event.getEnd(),
                event.getPublishedOn()
        );
    }

    public static AcceptedEvent toAcceptedEvent(SuggestedEvent event, Moderator moderator) {
        AcceptedEvent acceptedEvent = new AcceptedEvent();
        acceptedEvent.setTitle(event.getTitle());
        acceptedEvent.setDescription(event.getDescription());
        acceptedEvent.setLocation(event.getLocation());
        acceptedEvent.setStart(event.getStart());
        acceptedEvent.setEnd(event.getEnd());
        acceptedEvent.setNeighborhood(moderator.getNeighborhood());
        return acceptedEvent;
    }

    public static AcceptedEvent toAcceptedEvent(EventIDTO dto, Moderator moderator) {
        AcceptedEvent acceptedEvent = new AcceptedEvent();
        acceptedEvent.setTitle(dto.getTitle());
        acceptedEvent.setDescription(dto.getDescription());
        acceptedEvent.setLocation(dto.getLocation());
        acceptedEvent.setStart(dto.getStart());
        acceptedEvent.setEnd(dto.getEnd());
        acceptedEvent.setNeighborhood(moderator.getNeighborhood());
        return acceptedEvent;
    }

    public static CouncilReportDTO toCouncilReportDTO(CouncilReport report) {
        return new CouncilReportDTO(
                report.getId(),
                report.getContent(),
                report.getHeldOn(),
                report.getPublishedOn(),
                toResidentDTO(report.getPublishedBy())
        );
    }

    public static CouncilReport toCouncilReport(CouncilReportIDTO dto, Councilor councilor) {
        CouncilReport report = new CouncilReport();
        report.setContent(dto.getContent());
        report.setHeldOn(dto.getHeldOn());
        report.setPublishedBy(councilor);
        report.setNeighborhood(councilor.getStreet().getNeighborhood());
        return report;
    }
}
