package alexiuscrow.diploma.dao.plus.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.plus.DiscountsPlusDAO;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.entity.plus.DiscountsPlus;
import alexiuscrow.diploma.util.GeoFinder;
import alexiuscrow.diploma.util.HibernateUtil;

public class DiscountsPlusDAOImpl implements DiscountsPlusDAO {

	@Override
	public List<DiscountsPlus> getNearestDiscounts(Double lat, Double lng, Integer radius) 
			throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		List<DiscountsPlus> discountsPlus = null;
	    List<Shops> shops = null;
	    
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_nearest_shops_only(?,?,?);")
	    		   .addEntity(Shops.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, radius.toString());
	       shops = query.list(); 
	       
	       discountsPlus = DiscountsPlus.eatNourishing(shops, lat, lng, session);;
	       
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return discountsPlus;
	}

	@Override
	public List<DiscountsPlus> getAllLocalityDiscounts(Double lat, Double lng)
			throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		List<DiscountsPlus>  discountsPlus = null;
		List<Shops>  shops = null;
		
	    String localityName = GeoFinder.getLocalityName(lat, lng);
//	    String localityName = "Chernihiv";
	    
	    if (localityName == null)
	    	return null;
	    
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_locality_shops_only(?,?,?);")
	    		   .addEntity(Shops.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, localityName);
	       shops = query.list();
	       
	       discountsPlus = DiscountsPlus.eatNourishing(shops, lat, lng, session);
	       
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return discountsPlus;
	}

}
