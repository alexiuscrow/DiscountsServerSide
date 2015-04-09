package alexiuscrow.diploma.dao;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.Discounts;


public interface DiscountsDAO {
	public List<Discounts> getAllDiscounts() throws SQLException;
	public String getNearestDiscounts(Double lat, Double lng, Integer radius) throws SQLException;
	public String getAllLocalityDiscounts(Double lat, Double lng) throws SQLException;
}
