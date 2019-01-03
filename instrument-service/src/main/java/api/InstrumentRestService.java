package api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.model.Instrument;
import domain.service.InstrumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@ApplicationScoped
@Path("/instrument")
@Api(value = "instrument", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class InstrumentRestService {

	@Inject
	private InstrumentService instrumentService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the instruments",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public List<Instrument> getAll() {
		return instrumentService.getAll();
	}

}