package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.entity.plus.DiscountsPlus;
import alexiuscrow.diploma.util.Validator;

import com.google.common.collect.Range;


@Path("/shops/discounts")
public class DiscountsResources {


	@GET
	@Produces("application/json")
	public List<DiscountsPlus> getDiscounts(
			@QueryParam("radius") String radiusParam, 
			@QueryParam("lat") String latParam,
			@QueryParam("lng") String lngParam) throws SQLException{
		
		if (Validator.isItDouble(latParam) && Validator.isItDouble(lngParam)){
			Range<Integer> rangeRadius = Range.atMost(1000);
			
			List<DiscountsPlus> discounts = null;
			Double lat = Double.valueOf(latParam);
			Double lng = Double.valueOf(lngParam);
			
			if (Validator.isItInteger(radiusParam) && rangeRadius.contains(Integer.parseInt(radiusParam))){
				//return discounts in a nearest locality
				int radius = Integer.valueOf(radiusParam);
				discounts = Factory.getInstance().getDiscountsPlus().getNearestDiscounts(lat, lng, radius);
				return discounts;
			}
			else {
				//return all discounts in a locality
				return Factory.getInstance().getDiscountsPlus().getAllLocalityDiscounts(lat, lng);
			}
		}
		else{
			throw new WebApplicationException(400);
		}
	}
}
