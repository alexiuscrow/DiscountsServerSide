package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.util.Validator;

import com.google.common.collect.Range;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/shops")
@Api(value = "Shops", description = "Operations about shops")
public class ShopsResources {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "List shops", httpMethod = "GET", notes = "List nearest or locality shops",
	response = Shops.class, responseContainer = "List")
//	@ApiOperation(value = "List shops", httpMethod = "GET", notes = "List nearest or locality shops")
	public String getShops(
			@ApiParam( value = "Latitude", required = true)
			@DefaultValue("51.522256")
			@QueryParam("lat") 
			String latParam,
			@ApiParam( value = "Longitude", required = true)
			@DefaultValue("31.229335")
			@QueryParam("lng")
			String lngParam,
			@ApiParam( value = "Radius", required = false)
			@QueryParam("radius") 
			String radiusParam) throws SQLException{
		
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
