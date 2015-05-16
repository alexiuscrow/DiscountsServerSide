package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.entity.Shops;

import com.google.common.collect.Range;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/shops")
@Api(value = "Shops", description = "Operations about shops")
public class ShopsResources {
	private final Double MIN_RADIUS_VALUE = 0d;
	private final Double MAX_RADIUS_VALUE = 1000d;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok", response = Shops.class),
			@ApiResponse(code = 204, message = "No content"),
			@ApiResponse(code = 400, message = "Some parameters does not have the appropriate format or radius goes beyond the range.")
			})
	@ApiOperation(value = "List shops", httpMethod = "GET", notes = "List nearest or locality shops",
	response = Shops.class, responseContainer = "List")
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
		
		try{
			Double lat = Double.valueOf(latParam);
			Double lng = Double.valueOf(lngParam);
			Range<Double> rangeRadius = Range.closed(MIN_RADIUS_VALUE, MAX_RADIUS_VALUE);
			
			//If the radius was specified
			if (null != radiusParam){
				try{
					if (rangeRadius.contains(Double.parseDouble(radiusParam))){
						Double radius = Double.valueOf(radiusParam);
						return Factory.getInstance().getShops().getNearestShops(lat, lng, radius);
					}
					else{
						//Radius goes beyond the range
						throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
								.entity("Radius goes beyond the range. Min value: " + rangeRadius.lowerEndpoint()
										+ ". Max value: " + rangeRadius.upperEndpoint() + ".").type(MediaType.TEXT_PLAIN).build());
					}
				}
				catch(NumberFormatException e){
					//Radius does not have the appropriate format
					throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
							.entity("Radius does not have the appropriate format.").type(MediaType.TEXT_PLAIN).build());
				}
			}
			else {
				return Factory.getInstance().getShops().getAllLocalityShops(lat, lng);
			}
		}
		catch(NumberFormatException e){
			//Latitude or longitude does not have the appropriate format
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
					.entity("Latitude or longitude does not have the appropriate format.").type(MediaType.TEXT_PLAIN).build());
		}
	}
}