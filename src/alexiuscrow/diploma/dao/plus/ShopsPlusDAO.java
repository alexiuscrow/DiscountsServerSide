package alexiuscrow.diploma.dao.plus;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.plus.ShopsPlus;

public interface ShopsPlusDAO {
	public List<ShopsPlus> getNearestShops(Double lat, Double lng, Integer radius) throws SQLException;
	public List<ShopsPlus> getAllLocalityShops(Double lat, Double lng) throws SQLException;
}
