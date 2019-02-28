package api.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.msg.InstrumentProducer;
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
	@Inject
	private InstrumentProducer instrumentProducer;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the instruments",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public List<Instrument> getAll() {
		return instrumentService.getAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public Instrument get(@PathParam("id") Long instrumentId) {
		return instrumentService.get(instrumentId);
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a given instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void upadte(Instrument instrument) {
		instrumentService.update(instrument);
		instrumentProducer.send(instrument);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void create(Instrument instrument) {
		instrumentService.create(instrument);
		instrumentProducer.send(instrument);
	}

	
	@POST
	@Path("propagateAllInstruments")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Propagate all instruments to the bus to sync up downstream services",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void propagateAllInstruments() {
		instrumentProducer.sendAllInstruments();
	}
}