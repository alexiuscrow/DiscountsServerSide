package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.util.Validator;

import com.google.common.collect.Range;


@Path("/shops/discounts")
public class DiscountsResources {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getDiscounts(
			@QueryParam("radius") String radiusParam, 
			@QueryParam("lat") String latParam,
			@QueryParam("lng") String lngParam) throws SQLException{
		
		if (Validator.isItDouble(latParam) && Validator.isItDouble(lngParam)){
			Range<Integer> rangeRadius = Range.atMost(1000);
			
			Double lat = Double.valueOf(latParam);
			Double lng = Double.valueOf(lngParam);
			
			if (Validator.isItInteger(radiusParam) && rangeRadius.contains(Integer.parseInt(radiusParam))){
				//return discounts in a nearest locality
				int radius = Integer.valueOf(radiusParam);
				return Factory.getInstance().getDiscounts().getNearestDiscounts(lat, lng, radius);
			}
			else {
				//return all discounts in a locality
				return Factory.getInstance().getDiscounts().getAllLocalityDiscounts(lat, lng);
			}
		}
		else{
			throw new WebApplicationException(400);
		}
	}
}
