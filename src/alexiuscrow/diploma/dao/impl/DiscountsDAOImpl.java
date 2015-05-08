package alexiuscrow.diploma.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.DiscountsDAO;
import alexiuscrow.diploma.entity.Discounts;
import alexiuscrow.diploma.entity.Localities;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.util.GeoFinder;
import alexiuscrow.diploma.util.HibernateUtil;
import alexiuscrow.diploma.util.serializers.NearestShopsSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DiscountsDAOImpl implements DiscountsDAO {

	@Override
	public List<Discounts> getAllDiscounts() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	    List discounts = null;
	    try{
	       tx = session.beginTransaction();
	       discounts = session.createQuery("FROM Discounts").list(); 
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
	public String getNearestDiscounts(Double lat, Double lng, Integer radius) 
			throws SQLException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Localities> localities = null;
		String result = null;
	    
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_nearest_shops_only(?,?,?);")
	    		   .addEntity(Shops.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, radius.toString());
	       
	       List<Shops> shops = query.list(); 
	       putDistance(shops, lat, lng);
	       Gson gson = new GsonBuilder().registerTypeAdapter(Shops.class, new NearestShopsSerializer())
	    		   .excludeFieldsWithoutExposeAnnotation()
	    		   .setPrettyPrinting().create();
		   result = gson.toJson(shops);
	       
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return result;
	}

	@Override
	public String getAllLocalityDiscounts(Double lat, Double lng)
			throws SQLException {
		
		String localityName = GeoFinder.getLocalityName(lat, lng);
		
		if (localityName == null)
			return null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Localities>  localities = null;
		String result = null;
	    
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_locality_shops_only(?,?,?);")
	    		   .addEntity(Shops.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, localityName);
	       
	       List<Shops> shops = query.list();
	       Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").setPrettyPrinting().create();
		   result = gson.toJson(shops);
		   
	       tx.commit();
	       
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    } finally {
	       session.close(); 
	    }
        return result;
	}
	
	private void putDistance(List<Shops> shops, Double lat, Double lng){
		for(Shops shop: shops){
	    	   shop.setDistance(GeoFinder.getDistance(lat, lng, shop));
	    }
	}
	
	public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

}
