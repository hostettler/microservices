package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import domain.model.Instrument;
import domain.model.PortforlioStatistics;

@ApplicationScoped
public class ValuationServiceImpl implements ValuationService {

	@Override
	public PortforlioStatistics valuatePortfolio(List<Instrument> portfolio) {
		// TODO Auto-generated method stub
		return null;
	}

}
