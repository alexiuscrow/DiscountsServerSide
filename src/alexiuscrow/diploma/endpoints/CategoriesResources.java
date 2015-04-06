package alexiuscrow.diploma.endpoints;

import java.util.EnumSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import alexiuscrow.diploma.entity.enums.Categories;

import com.google.gson.Gson;


@Path("/categories")
public class CategoriesResources {
	
	@GET
	@Produces("application/json")
	public String getCategories(){
		return new Gson().toJson(EnumSet.allOf(Categories.class));
	}
}