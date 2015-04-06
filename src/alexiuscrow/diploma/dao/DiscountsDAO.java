package alexiuscrow.diploma.dao;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.Discounts;


public interface DiscountsDAO {
	public List<Discounts> getAllDiscounts() throws SQLException;
}
