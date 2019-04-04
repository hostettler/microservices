package api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.Counterparty;
import domain.service.CounterpartyService;
import io.swagger.annotations.ApiOperation;

@ApplicationScoped
@Path("/counterparties")
public class CounterpartyRestService {

	@Inject
	private CounterpartyService counterpartyService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all coutnerparties")
	public List<Counterparty> getAll() {
		return counterpartyService.getAll();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Count the number of counterparties")
	public Long count() {
		return counterpartyService.count();
	}
	
	@GET
	@Path("{lei}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specific counterparty using its lei")
	public Counterparty get(@PathParam("lei") String lei) {
		return counterpartyService.get(lei);
	}

}