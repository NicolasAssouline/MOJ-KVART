package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Administrator;
import hr.fer.opp.mojkvart.rest.dto.in.UserIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.UserDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static hr.fer.opp.mojkvart.rest.util.ControllerUtil.toUserDTO;

@RestController
@RequestMapping("/users/admins")
@Secured("ROLE_ADMIN")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("")
    public List<UserDTO> getAdministrators() {
        return administratorService.listAll().stream()
                .map(ControllerUtil::toUserDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getAdministrator(@PathVariable Integer id) {
        return toUserDTO(administratorService.fetch(id));
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createAdministrator(@RequestBody UserIDTO dto) {
        UserDTO administrator = toUserDTO(administratorService.add(toAdministrator(dto)));
        return ResponseEntity.created(URI.create(
                "/users/administrators/" + administrator.getId())).body(administrator);
    }

    private Administrator toAdministrator(UserIDTO dto) {
        Administrator administrator = new Administrator();
        administrator.setUsername(dto.getUsername());
        administrator.setPasswordHash(dto.getPassword());
        return administrator;
    }
}
