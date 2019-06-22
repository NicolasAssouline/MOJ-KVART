package hr.fer.opp.mojkvart.service.util;

import hr.fer.opp.mojkvart.dao.ResidentRepository;
import hr.fer.opp.mojkvart.dao.UserRepository;
import hr.fer.opp.mojkvart.domain.Event;
import hr.fer.opp.mojkvart.domain.Resident;
import hr.fer.opp.mojkvart.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

public final class ServiceUtil {

    /** Permits all except the control characters (such as new lines). */
    public static final Pattern NAME = Pattern.compile("^[^\\p{Cntrl}]+$");

    /** Minimum eight characters, at least one letter and one number. */
    private static final Pattern PASSWORD = Pattern.compile(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    /** RFC 5322 compliant email regex with some security modifications. */
    private static final Pattern EMAIL = Pattern.compile(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    private ServiceUtil() {
    }

    public static void checkAddUser(User user, PasswordEncoder passwordEncoder) {
        Assert.isNull(user.getId(), "User ID must be null.");
        checkModifyUser(user, passwordEncoder);
        user.setCreatedOn(LocalDateTime.now());
    }

    private static void checkModifyUser(User user, PasswordEncoder passwordEncoder) {
        String username = user.getUsername();
        Assert.hasLength(username, "Username is required.");

        String password = user.getPasswordHash();
        Assert.hasLength(password, "Password is required.");
        Assert.isTrue(PASSWORD.matcher(password).matches(),
                "Password must contain minimum eight characters, at least one letter and one number.");
        user.setPasswordHash(passwordEncoder.encode(password));
    }

    public static void checkAddResident(Resident resident, PasswordEncoder passwordEncoder) {
        Assert.isNull(resident.getId(), "Resident ID must be null.");
        checkModifyResident(resident, passwordEncoder);

        resident.setCreatedOn(LocalDateTime.now());
    }

    public static void checkModifyResident(Resident resident, PasswordEncoder passwordEncoder) {
        checkModifyUser(resident, passwordEncoder);

        Assert.hasLength(resident.getFirstName(), "First name is required.");
        Assert.hasLength(resident.getLastName(), "Last name is required.");

        String email = resident.getEmail();
        Assert.hasLength(email, "Email is required.");
        Assert.isTrue(EMAIL.matcher(email).matches(), "Invalid email pattern.");

        Assert.notNull(resident.getStreet(), "Street is required.");
    }

    public static void checkAddEvent(Event event) {
        Assert.isNull(event.getId(), "Event ID must be null.");
        Assert.hasLength(event.getTitle(), "Title is required.");
        Assert.hasLength(event.getDescription(), "Description is required.");
        Assert.hasLength(event.getLocation(), "Location is required.");

        LocalDateTime start = event.getStart();
        Assert.notNull(event.getStart(), "Start time is required.");

        LocalDateTime end = event.getEnd();
        if (end != null) {
            Assert.isTrue(start.isBefore(end), "Start time must be before end time.");
        }
    }

    public static void checkUsernameTaken(User user, UserRepository repository) {
        String username = user.getUsername();
        Optional<User> found = repository.findByUsername(username);
        if (found.isPresent() && !found.get().equals(user)) {
            throw new IllegalArgumentException("Username " + username + " already taken.");
        }
    }

    public static void checkEmailTaken(Resident resident, ResidentRepository repository) {
        String email = resident.getEmail();
        Optional<Resident> found = repository.findByEmail(email);
        if (found.isPresent() && !found.get().equals(resident)) {
            throw new IllegalArgumentException("Email " + email + " already taken.");
        }
    }
}
