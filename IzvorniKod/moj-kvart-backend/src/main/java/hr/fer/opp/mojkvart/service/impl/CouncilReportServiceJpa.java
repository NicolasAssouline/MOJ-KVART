package hr.fer.opp.mojkvart.service.impl;

import hr.fer.opp.mojkvart.dao.CouncilReportRepository;
import hr.fer.opp.mojkvart.domain.CouncilReport;
import hr.fer.opp.mojkvart.service.CouncilReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouncilReportServiceJpa implements CouncilReportService {

    @Autowired
    private CouncilReportRepository repository;

    @Override
    public List<CouncilReport> listAll() {
        return repository.findAll();
    }

    @Override
    public CouncilReport add(CouncilReport councilReport) {
        checkAdd(councilReport);
        return repository.save(councilReport);
    }

    @Override
    public CouncilReport fetch(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Councilor report with ID " + id + " does not exist."));
    }

    private void checkAdd(CouncilReport councilReport) {
        Assert.isNull(councilReport.getId(), "Council report ID must be null.");
        Assert.hasLength(councilReport.getContent(), "Content is required.");
        Assert.notNull(councilReport.getHeldOn(), "Held on field is required.");
        Assert.notNull(councilReport.getPublishedBy(), "Report author is required.");
        Assert.notNull(councilReport.getNeighborhood(), "Neighborhood is required.");
        councilReport.setPublishedOn(LocalDateTime.now());
    }
}
