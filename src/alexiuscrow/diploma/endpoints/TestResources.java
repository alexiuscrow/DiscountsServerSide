package alexiuscrow.diploma.endpoints;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import alexiuscrow.diploma.dao.Factory;
import alexiuscrow.diploma.entity.plus.DiscountsPlus;

@Path("/test")
@Produces("application/json")
public class TestResources {

	@GET
	public List<DiscountsPlus> showTest() {
		List<DiscountsPlus> discounts = null;
		try {
			discounts =  Factory.getInstance().getDiscountsPlus().getNearestDiscounts(51.522256, 31.229335, 489);
//			discounts = return Factory.getInstance().getDiscountsPlus().getNearestDiscounts(51.522256, 31.229335, "Chernihiv");;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return discounts;
	}

}
