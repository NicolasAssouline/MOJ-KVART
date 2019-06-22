package hr.fer.opp.mojkvart.service;

import hr.fer.opp.mojkvart.domain.CouncilReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouncilReportService {
	
	List<CouncilReport> listAll();
	
	CouncilReport add(CouncilReport councilReport);
	
	CouncilReport fetch(Integer id);
}
