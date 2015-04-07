package alexiuscrow.diploma.dao.plus.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.plus.DiscountsPlusDAO;
import alexiuscrow.diploma.entity.plus.DiscountsPlus;
import alexiuscrow.diploma.util.GeoFinder;
import alexiuscrow.diploma.util.HibernateUtil;

public class DiscountsPlusDAOImpl implements DiscountsPlusDAO {

	@Override
	public List<DiscountsPlus> getNearestDiscounts(Double lat, Double lng, Integer radius) 
			throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setDefaultReadOnly(false);
		Transaction tx = null;
	    List discounts = null;
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_nearest_discounts(?,?,?);")
	    		   .addEntity(DiscountsPlus.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, radius.toString());
	       discounts = query.setReadOnly(true).list(); 
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return discounts;
	}

	@Override
	public List<DiscountsPlus> getAllLocalityDiscounts(Double lat, Double lng)
			throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setDefaultReadOnly(false);
		Transaction tx = null;
	    List discounts = null;
	    String localityName = GeoFinder.getLocalityName(lat, lng);
	    
	    if (localityName == null)
	    	return null;
	    
	    
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_locality_discounts(?,?,?);")
	    		   .addEntity(DiscountsPlus.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, localityName).setReadOnly(true);
	       discounts = query.list(); 
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return discounts;
	}

}
