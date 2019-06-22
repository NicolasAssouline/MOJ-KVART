package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Councilor;
import hr.fer.opp.mojkvart.rest.dto.in.ResidentIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.ResidentDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.CouncilorService;
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
@RequestMapping("/users/councilors")
@Secured("ROLE_ADMIN")
public class CouncilorController {

    @Autowired
    private CouncilorService councilorService;

    @Autowired
    private StreetService streetService;

    @GetMapping("")
    public List<ResidentDTO> getCouncilors() {
        return councilorService.listAll().stream()
                .map(ControllerUtil::toResidentDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResidentDTO getCouncilor(@PathVariable Integer id) {
        return toResidentDTO(councilorService.fetch(id));
    }

    @PostMapping("")
    public ResponseEntity<ResidentDTO> createCouncilor(
            @RequestBody ResidentIDTO dto) {
        ResidentDTO councilor = toResidentDTO(councilorService.add(toCouncilor(dto)));
        return ResponseEntity.created(URI.create(
                "/users/councilors/" + councilor.getId())).body(councilor);
    }

    private Councilor toCouncilor(ResidentIDTO dto) {
        Councilor councilor = new Councilor();
        ControllerUtil.toResident(dto, councilor, streetService);
        return councilor;
    }
}
