package domain.service;

import domain.model.PortforlioStatistics;

public interface ValuationService {

	PortforlioStatistics valuatePortfolio(String currency);

}