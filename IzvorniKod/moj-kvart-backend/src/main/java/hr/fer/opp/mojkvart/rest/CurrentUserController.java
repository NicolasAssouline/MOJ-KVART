package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Resident;
import hr.fer.opp.mojkvart.rest.dto.in.ResidentIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.ResidentDTO;
import hr.fer.opp.mojkvart.rest.dto.out.UserDTO;
import hr.fer.opp.mojkvart.service.ResidentService;
import hr.fer.opp.mojkvart.service.StreetService;
import hr.fer.opp.mojkvart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static hr.fer.opp.mojkvart.rest.util.ControllerUtil.*;

@RestController
@RequestMapping("/user")
public class CurrentUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private StreetService streetService;

    @GetMapping("")
    public ResponseEntity<UserDTO> currentUser(@AuthenticationPrincipal User user) {
        if (user == null || user.getAuthorities() == null
                || user.getAuthorities().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(toUserDTO(loggedInUser(userService)));
    }

    @GetMapping("/info")
    @Secured("ROLE_RESIDENT")
    public ResidentDTO currentResident() {
        return toResidentDTO(loggedInResident(residentService));
    }

    @PutMapping("/info")
    @Secured("ROLE_RESIDENT")
    public ResidentDTO modifyCurrentResident(@RequestBody ResidentIDTO dto) {
        Resident current = loggedInResident(residentService);
        toResident(dto, current, streetService);
        return toResidentDTO(residentService.modify(current));
    }
}
