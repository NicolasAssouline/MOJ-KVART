package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Street;
import hr.fer.opp.mojkvart.rest.dto.in.StreetIDTO;
import hr.fer.opp.mojkvart.service.NeighborhoodService;
import hr.fer.opp.mojkvart.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/streets")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @Autowired
    private NeighborhoodService neighborhoodService;

    @GetMapping("")
    public List<Street> getStreets() {
        return streetService.listAll();
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public Street newStreet(@RequestBody StreetIDTO dto) {
        return streetService.add(toStreet(dto));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteStreet(@PathVariable Integer id) {
        streetService.delete(id);
        return ResponseEntity.ok().build();
    }

    private Street toStreet(StreetIDTO dto) {
        Street street = new Street();
        String name = dto.getName();
        if (streetService.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Street " + name + " already exists.");
        }
        street.setName(dto.getName());

        String neighborhood = dto.getNeighborhood();
        street.setNeighborhood(neighborhoodService.findByName(neighborhood)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Neighborhood " + neighborhood + "does not exist.")));
        return street;
    }
}
