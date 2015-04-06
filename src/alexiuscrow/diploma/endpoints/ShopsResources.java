package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.entity.plus.ShopsPlus;
import alexiuscrow.diploma.util.Validator;

import com.google.common.collect.Range;


@Path("/shops")
public class ShopsResources {


	@GET
	@Produces("application/json")
	public List<ShopsPlus> getShops(
			@QueryParam("radius") String radiusParam, 
			@QueryParam("lat") String latParam,
			@QueryParam("lng") String lngParam) throws SQLException{
		
		if (Validator.isItDouble(latParam) && Validator.isItDouble(lngParam)){
			Range<Integer> rangeRadius = Range.atMost(1000);
			
			List<ShopsPlus> shops = null;
			Double lat = Double.valueOf(latParam);
			Double lng = Double.valueOf(lngParam);
			
			if (Validator.isItInteger(radiusParam) && rangeRadius.contains(Integer.parseInt(radiusParam))){
				//return shops in a nearest shops
				int radius = Integer.valueOf(radiusParam);
				shops = Factory.getInstance().getShopsPlus().getNearestShops(lat, lng, radius);
				return shops;
			}
			else {
				//return all shops in a locality
				return Factory.getInstance().getShopsPlus().getAllLocalityShops(lat, lng);
			}
		}
		else{
			throw new WebApplicationException(400);
		}
	}
	

}
