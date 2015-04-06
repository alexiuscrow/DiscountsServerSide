package alexiuscrow.diploma.dao;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.Shops;

public interface ShopsDAO {    
    public List<Shops> getAllShops() throws SQLException;
}
