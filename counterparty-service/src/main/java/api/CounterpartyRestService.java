package api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.Counterparty;
import domain.service.CounterpartyService;

@ApplicationScoped
@Path("/counterparties")
public class CounterpartyRestService {

	@Inject
	private CounterpartyService counterpartyService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Counterparty> getAll() {
		return counterpartyService.getAll();
	}

}