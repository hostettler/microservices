package api.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import domain.service.ValuationService;

@ApplicationScoped
@Path("/valuation")
public class ValuationRestService {

	@Inject
	private ValuationService valuationService;



}