package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Resident;
import hr.fer.opp.mojkvart.rest.dto.in.ResidentIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.ResidentDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.ResidentService;
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
@RequestMapping("/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @Autowired
    private StreetService streetService;

    @GetMapping("")
    @Secured("ROLE_RESIDENT")
    public List<ResidentDTO> listResidents() {
        return residentService.listAll().stream()
                .map(ControllerUtil::toResidentDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Secured("ROLE_RESIDENT")
    public ResidentDTO getResident(@PathVariable("id") Integer id) {
        return toResidentDTO(residentService.fetch(id));
    }

    @PostMapping("")
    public ResponseEntity<ResidentDTO> register(
            @RequestBody ResidentIDTO dto) {
        ResidentDTO resident = toResidentDTO(residentService.add(toResident(dto)));
        return ResponseEntity.created(
                URI.create("/residents/" + resident.getId())).body(resident);
    }

    private Resident toResident(ResidentIDTO dto) {
        Resident resident = new Resident();
        ControllerUtil.toResident(dto, resident, streetService);
        return resident;
    }
}
