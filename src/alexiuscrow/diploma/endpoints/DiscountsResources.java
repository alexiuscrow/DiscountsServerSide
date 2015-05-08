package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.util.Validator;

import com.google.common.collect.Range;


@Path("/shops/discounts")
public class DiscountsResources {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscounts(
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
				String result = Factory.getInstance().getDiscounts().getNearestDiscounts(lat, lng, radius);
				return Response.ok(result, MediaType.APPLICATION_JSON).status(Status.OK).build();
			}
			else {
				//return all discounts in a locality
				String result = Factory.getInstance().getDiscounts().getAllLocalityDiscounts(lat, lng);
				return Response.ok(result, MediaType.APPLICATION_JSON).status(Status.OK).build();
			}
		}
		else{
			throw new WebApplicationException(400);
		}
	}
	
	public static String bytesToHex(byte[] bytes) {
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
