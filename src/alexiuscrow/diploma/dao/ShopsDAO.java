package alexiuscrow.diploma.dao;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.Shops;

public interface ShopsDAO {    
    public List<Shops> getAllShops() throws SQLException;
	public String getNearestShops(Double lat, Double lng, Integer radius) throws SQLException;
	public String getAllLocalityShops(Double lat, Double lng) throws SQLException;
}
