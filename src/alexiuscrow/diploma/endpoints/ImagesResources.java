package alexiuscrow.diploma.endpoints;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/images")
public class ImagesResources {
	
	@GET
	@Produces("image/*")
	@Path("{id}")
	public Response getImage(@PathParam("id") String id){

		File file = new File("/server/file_storage/images", id + ".jpg");
		
		if (!file.exists()){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(file, "image/jpeg").build();
	}
}

