package api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/regulatory")
public class RegulatoryRestService {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		return "regulated";
	}

}