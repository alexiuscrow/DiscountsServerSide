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


@Path("/shops")
public class ShopsResources {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getShops(
			@QueryParam("radius") String radiusParam, 
			@QueryParam("lat") String latParam,
			@QueryParam("lng") String lngParam) throws SQLException{
		
		if (Validator.isItDouble(latParam) && Validator.isItDouble(lngParam)){
			Range<Double> rangeRadius = Range.atMost(1000d);
			
			Double lat = Double.valueOf(latParam);
			Double lng = Double.valueOf(lngParam);
			
			if (Validator.isItDouble(radiusParam) && rangeRadius.contains(Double.parseDouble(radiusParam))){
				//return shops in a nearest shops
				Double radius = Double.valueOf(radiusParam);
				return Factory.getInstance().getShops().getNearestShops(lat, lng, radius);
			}
			else {
				//return all shops in a locality
				return Factory.getInstance().getShops().getAllLocalityShops(lat, lng);
			}
		}
		else{
			throw new WebApplicationException(400);
		}
	}
	
}
