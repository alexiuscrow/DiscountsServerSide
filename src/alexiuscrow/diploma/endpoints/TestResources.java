package alexiuscrow.diploma.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestResources {

	@GET
	public String showTest() {
		return "Nothing here";
	}

}
