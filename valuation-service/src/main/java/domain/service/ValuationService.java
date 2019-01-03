package domain.service;

import java.util.List;

import domain.model.Instrument;
import domain.model.PortforlioStatistics;

public interface ValuationService {

	PortforlioStatistics valuatePortfolio(List<Instrument> portfolio);

}