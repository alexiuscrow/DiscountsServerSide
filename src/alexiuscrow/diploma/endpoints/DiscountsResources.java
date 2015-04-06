package alexiuscrow.diploma.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import alexiuscrow.diploma.entity.Discounts;
import alexiuscrow.diploma.util.Validator;

import com.google.common.collect.Range;


@Path("/shops/discounts")
public class DiscountsResources {


	@GET
	@Produces("application/json")
	public Discounts getDiscounts(
			@QueryParam("radius") String radiusParam, 
			@QueryParam("lat") String latParam,
			@QueryParam("lng") String lngParam){
		
		if (Validator.isItDouble(latParam) && Validator.isItDouble(lngParam)){
			Range<Integer> rangeRadius = Range.atMost(1000);
			
			if (Validator.isItInteger(radiusParam) && rangeRadius.contains(Integer.parseInt(radiusParam))){
				//return discounts in a nearest shops
			}
			else {
				//return all discount in a city
			}
		}
		else{
			throw new WebApplicationException(400);
		}
						
		return new Discounts();
	}
}
