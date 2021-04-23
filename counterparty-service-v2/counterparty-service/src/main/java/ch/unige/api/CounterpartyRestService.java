package ch.unige.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.unige.domain.model.Counterparty;
import ch.unige.domain.service.CounterpartyService;

@ApplicationScoped
@Path("/counterparties")
public class CounterpartyRestService {

	@Inject
	CounterpartyService counterpartyService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Counterparty> getAll() {
		return counterpartyService.getAll();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Long count() {
		return counterpartyService.count();
	}
	
	@GET
	@Path("{lei}")
	@Produces(MediaType.APPLICATION_JSON)
	public Counterparty get(@PathParam("lei") String lei) {
		return counterpartyService.get(lei);
	}

}