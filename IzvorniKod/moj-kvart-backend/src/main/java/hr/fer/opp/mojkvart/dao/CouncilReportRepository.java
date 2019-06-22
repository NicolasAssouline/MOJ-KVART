package hr.fer.opp.mojkvart.dao;

import hr.fer.opp.mojkvart.domain.CouncilReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouncilReportRepository extends JpaRepository<CouncilReport, Integer> {
}