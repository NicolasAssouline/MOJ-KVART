package hr.fer.opp.mojkvart.rest;

import hr.fer.opp.mojkvart.domain.Neighborhood;
import hr.fer.opp.mojkvart.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hood")
public class NeighborhoodController {

    @Autowired
    private NeighborhoodService neighborhoodService;

    @GetMapping("")
    public List<Neighborhood> getNeighborhoods() {
        return neighborhoodService.listAll();
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public Neighborhood newNeighborhood(@RequestBody String name) {
        if (neighborhoodService.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Neighborhood " + name + " already exists.");
        }
        return neighborhoodService.add(toNeighborhood(name));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteNeighborhood(@PathVariable Integer id) {
        neighborhoodService.delete(id);
        return ResponseEntity.ok().build();
    }

    private Neighborhood toNeighborhood(String name) {
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setName(name);
        return neighborhood;
    }
}
