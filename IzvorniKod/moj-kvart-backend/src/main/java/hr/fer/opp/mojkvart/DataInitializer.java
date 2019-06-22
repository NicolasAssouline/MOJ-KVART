package hr.fer.opp.mojkvart;

import hr.fer.opp.mojkvart.domain.*;
import hr.fer.opp.mojkvart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    @Autowired
    private AcceptedEventService acceptedEventService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CouncilorService councilorService;

    @Autowired
    private CouncilReportService councilReportService;

    @Autowired
    private ForumPostService forumPostService;

    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private NeighborhoodService neighborhoodService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private StreetService streetService;

    @Autowired
    private SuggestedEventService suggestedEventService;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Neighborhood neighborhood1 = new Neighborhood();
        neighborhood1.setName("Kvart 1");
        neighborhoodService.add(neighborhood1);

        Street street1 = new Street();
        street1.setName("Ulica 1");
        street1.setNeighborhood(neighborhood1);
        streetService.add(street1);

        Street street2 = new Street();
        street2.setName("Ulica 2");
        street2.setNeighborhood(neighborhood1);
        streetService.add(street2);

        Neighborhood neighborhood2 = new Neighborhood();
        neighborhood2.setName("Kvart 2");
        neighborhoodService.add(neighborhood2);

        Street street3 = new Street();
        street3.setName("Ulica 3");
        street3.setNeighborhood(neighborhood2);
        streetService.add(street3);

        Street street4 = new Street();
        street4.setName("Ulica 4");
        street4.setNeighborhood(neighborhood2);
        streetService.add(street4);

        Administrator admin = new Administrator();
        admin.setUsername("Administrator");
        admin.setPasswordHash("administrator1");
        admin.setCreatedOn(LocalDateTime.now());
        administratorService.add(admin);

        Resident resident1 = new Resident();
        resident1.setUsername("Stanovnik1");
        resident1.setPasswordHash("stanovnik1");
        resident1.setCreatedOn(LocalDateTime.now());
        resident1.setFirstName("Stanovnik");
        resident1.setLastName("Jedan");
        resident1.setEmail("stanovnik1@kvart.hr");
        resident1.setStreet(street1);
        residentService.add(resident1);

        Resident resident2 = new Resident();
        resident2.setUsername("Stanovnik2");
        resident2.setPasswordHash("stanovnik2");
        resident2.setCreatedOn(LocalDateTime.now());
        resident2.setFirstName("Stanovnik");
        resident2.setLastName("Dva");
        resident2.setEmail("stanovnik2@kvart.hr");
        resident2.setStreet(street2);
        residentService.add(resident2);

        Resident resident3 = new Resident();
        resident3.setUsername("Stanovnik3");
        resident3.setPasswordHash("stanovnik3");
        resident3.setCreatedOn(LocalDateTime.now());
        resident3.setFirstName("Stanovnik");
        resident3.setLastName("Tri");
        resident3.setEmail("stanovnik3@kvart.hr");
        resident3.setStreet(street3);
        residentService.add(resident3);

        Resident resident4 = new Resident();
        resident4.setUsername("Stanovnik4");
        resident4.setPasswordHash("stanovnik4");
        resident4.setCreatedOn(LocalDateTime.now());
        resident4.setFirstName("Stanovnik");
        resident4.setLastName("Četiri");
        resident4.setEmail("stanovnik4@kvart.hr");
        resident4.setStreet(street4);
        residentService.add(resident4);

        Moderator moderator1 = new Moderator();
        moderator1.setUsername("Moderator1");
        moderator1.setPasswordHash("moderator1");
        moderator1.setCreatedOn(LocalDateTime.now());
        moderator1.setFirstName("Moderator");
        moderator1.setLastName("Jedan");
        moderator1.setEmail("moderator1@gmail.com");
        moderator1.setStreet(street1);
        moderatorService.add(moderator1);

        Moderator moderator2 = new Moderator();
        moderator2.setUsername("Moderator2");
        moderator2.setPasswordHash("moderator2");
        moderator2.setCreatedOn(LocalDateTime.now());
        moderator2.setFirstName("Moderator");
        moderator2.setLastName("Dva");
        moderator2.setEmail("moderator2@gmail.com");
        moderator2.setStreet(street3);
        moderatorService.add(moderator2);

        Councilor councilor1 = new Councilor();
        councilor1.setUsername("Councilor1");
        councilor1.setPasswordHash("vijecnik11");
        councilor1.setCreatedOn(LocalDateTime.now());
        councilor1.setFirstName("Vijećnik");
        councilor1.setLastName("Jedan");
        councilor1.setEmail("councilor1@gmail.com");
        councilor1.setStreet(street1);
        councilorService.add(councilor1);

        Councilor councilor2 = new Councilor();
        councilor2.setUsername("Councilor2");
        councilor2.setPasswordHash("vijecnik22");
        councilor2.setCreatedOn(LocalDateTime.now());
        councilor2.setFirstName("Vijećnik");
        councilor2.setLastName("Dva");
        councilor2.setEmail("councilor2@gmail.com");
        councilor2.setStreet(street3);
        councilorService.add(councilor2);

        SuggestedEvent suggestedEvent1 = new SuggestedEvent();
        suggestedEvent1.setLocation("Lokacija broj 1");
        suggestedEvent1.setTitle("Događanje broj 1");
        suggestedEvent1.setDescription("Opis broj 1");
        suggestedEvent1.setStart(LocalDateTime.of(2021, 1, 1, 8, 0));
        suggestedEvent1.setNeighborhood(resident1.getNeighborhood());
        suggestedEvent1.setSuggestedBy(resident1);
        suggestedEvent1.setSuggestedOn(LocalDateTime.now());
        suggestedEventService.add(suggestedEvent1);

        SuggestedEvent suggestedEvent2 = new SuggestedEvent();
        suggestedEvent2.setLocation("Lokacija broj 1");
        suggestedEvent2.setTitle("Događanje broj 2");
        suggestedEvent2.setStart(LocalDateTime.of(2021, 1, 1, 8, 0));
        suggestedEvent2.setNeighborhood(resident1.getNeighborhood());
        suggestedEvent2.setSuggestedBy(resident1);
        suggestedEvent2.setSuggestedOn(LocalDateTime.now());
        suggestedEvent2.setDescription("opis broj 1");
        suggestedEventService.add(suggestedEvent2);

        AcceptedEvent acceptedEvent1 = new AcceptedEvent();
        acceptedEvent1.setLocation("Lokacija broj 2");
        acceptedEvent1.setTitle("Događanje broj 2");
        acceptedEvent1.setDescription("Opis broj 2");
        acceptedEvent1.setStart(LocalDateTime.of(2022, 1, 1, 8, 0));
        acceptedEvent1.setEnd(LocalDateTime.of(2022, 1, 1, 10, 0));
        acceptedEvent1.setNeighborhood(resident3.getNeighborhood());
        acceptedEvent1.setPublishedOn(LocalDateTime.now());
        acceptedEventService.add(acceptedEvent1);

        ForumThread thread1 = new ForumThread();
        thread1.setTitle("Prva tema");
        thread1.setOpenedOn(LocalDateTime.now());
        thread1.setOpenedBy(resident1);
        thread1.setNeighborhood(resident1.getNeighborhood());
        forumThreadService.add(thread1);

        ForumThread thread3 = new ForumThread();
        thread3.setTitle("Treca tema");
        thread3.setOpenedOn(LocalDateTime.now());
        thread3.setOpenedBy(resident2);
        thread3.setNeighborhood(resident2.getNeighborhood());
        forumThreadService.add(thread3);


        ForumPost post1 = new ForumPost();
        post1.setContent("Prvi post.\nU prvoj temi.");
        post1.setPostedOn(LocalDateTime.now());
        post1.setPostedBy(resident1);
        post1.setForumThread(thread1);
        forumPostService.add(post1);

        ForumPost post2 = new ForumPost();
        post2.setContent("Odgovor na prvi post u prvoj temi.");
        post2.setPostedOn(LocalDateTime.now());
        post2.setPostedBy(resident2);
        post2.setForumThread(thread1);
        forumPostService.add(post2);

        ForumThread thread2 = new ForumThread();
        thread2.setTitle("Tema koja se tiče drugog susjedstva");
        thread2.setOpenedOn(LocalDateTime.now());
        thread2.setOpenedBy(resident3);
        thread2.setNeighborhood(resident3.getNeighborhood());
        forumThreadService.add(thread2);

        ForumPost post3 = new ForumPost();
        post3.setContent("Post u temi.");
        post3.setPostedOn(LocalDateTime.now());
        post3.setPostedBy(resident3);
        post3.setForumThread(thread2);
        forumPostService.add(post3);

        ForumPost post4 = new ForumPost();
        post4.setContent("Odgovor stavnovniku3.\nI to u njegovoj temi.");
        post4.setPostedOn(LocalDateTime.now());
        post4.setPostedBy(resident4);
        post4.setForumThread(thread2);
        forumPostService.add(post4);

        CouncilReport report1 = new CouncilReport();
        report1.setContent("Sadržaj prvog vijeća četvrti 1\nbla\nbla\nbla.");
        report1.setPublishedBy(councilor1);
        report1.setNeighborhood(councilor1.getNeighborhood());
        report1.setPublishedOn(LocalDateTime.now());
        report1.setHeldOn(LocalDateTime.of(2018, 1, 1, 12, 0));
        councilReportService.add(report1);

        CouncilReport report2 = new CouncilReport();
        report2.setContent("Sadržaj prvog vijeća četvrti 2\nbla\nbla\nbla.");
        report2.setPublishedBy(councilor2);
        report2.setNeighborhood(councilor2.getNeighborhood());
        report2.setPublishedOn(LocalDateTime.now());
        report2.setHeldOn(LocalDateTime.of(2017, 1, 1, 12, 0));
        councilReportService.add(report2);
    }
}
