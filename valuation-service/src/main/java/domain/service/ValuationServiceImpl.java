package domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import domain.model.Instrument;
import domain.model.PortforlioStatistics;

@ApplicationScoped
public class ValuationServiceImpl implements ValuationService {

	@Inject
	private InstrumentRepository repository;

	@Override
	public PortforlioStatistics valuatePortfolio(String currency) {
		PortforlioStatistics portforlioStatistics = new PortforlioStatistics(currency);

		for (Instrument instrument : repository.getAll()) {
			portforlioStatistics.add(instrument.getAmountInOriginalCurrency(), instrument.getType(),
					instrument.getOriginalCurrency());
		}

		return portforlioStatistics;
	}

}
