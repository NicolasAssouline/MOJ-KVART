package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.CouncilReport;
import hr.fer.opp.mojkvart.rest.dto.in.CouncilReportIDTO;
import hr.fer.opp.mojkvart.rest.dto.out.CouncilReportDTO;
import hr.fer.opp.mojkvart.rest.util.ControllerUtil;
import hr.fer.opp.mojkvart.service.CouncilReportService;
import hr.fer.opp.mojkvart.service.CouncilorService;
import hr.fer.opp.mojkvart.service.ResidentService;
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
@RequestMapping("/council")
public class CouncilReportController {

    @Autowired
    private CouncilReportService councilReportService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private CouncilorService councilorService;

    @GetMapping("")
    @Secured("ROLE_RESIDENT")
    public List<CouncilReportDTO> currentReports() {
        return loggedInResident(residentService).getNeighborhood()
                .getCouncilReports().stream()
                .map(ControllerUtil::toCouncilReportDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Secured("ROLE_RESIDENT")
    public ResponseEntity<CouncilReportDTO> getReport(@PathVariable Integer id) {
        CouncilReport report = councilReportService.fetch(id);
        return loggedInResident(residentService).getNeighborhood().getId()
                .equals(report.getNeighborhood().getId())
                ? ResponseEntity.ok(toCouncilReportDTO(report))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("")
    @Secured("ROLE_COUNCILOR")
    public ResponseEntity<CouncilReportDTO> newReport(@RequestBody CouncilReportIDTO dto) {
        CouncilReport report = councilReportService.add(
                toCouncilReport(dto, loggedInCouncilor(councilorService)));
        return ResponseEntity.created(URI.create("/council/" + report.getId()))
                .body(toCouncilReportDTO(report));
    }
}
