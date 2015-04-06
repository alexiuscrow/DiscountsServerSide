package alexiuscrow.diploma.dao.plus;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.plus.DiscountsPlus;

public interface DiscountsPlusDAO {
	public List<DiscountsPlus> getNearestDiscounts(Double lat, Double lng, Integer radius) throws SQLException;
	public List<DiscountsPlus> getAllLocalityDiscounts(Double lat, Double lng) throws SQLException;
}