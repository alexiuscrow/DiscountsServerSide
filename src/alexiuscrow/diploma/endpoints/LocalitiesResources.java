package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.entity.Localities;

@Path("/localities")
public class LocalitiesResources {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Localities getLocalities() throws SQLException{
		return Factory.getInstance().getLicalities().getAllLocalities().get(0);
	}

}
