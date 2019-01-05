package api.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import domain.model.PortforlioStatistics;
import domain.service.ValuationService;

@ApplicationScoped
@Path("/valuation")
public class ValuationRestService {

	@Inject
	private ValuationService valuationService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PortforlioStatistics valuatePortfolio(@QueryParam("currency") String currency) {
		return this.valuationService.valuatePortfolio(currency);
	}
	
}