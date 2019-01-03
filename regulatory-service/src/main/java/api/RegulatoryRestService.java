package api;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import domain.service.RegulatoryService;

@ApplicationScoped
@Path("/valuation")
public class RegulatoryRestService {

	@Inject
	private RegulatoryService valuationService;



}