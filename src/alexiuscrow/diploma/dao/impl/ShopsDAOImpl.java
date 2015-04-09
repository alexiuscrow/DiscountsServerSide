package alexiuscrow.diploma.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.ShopsDAO;
import alexiuscrow.diploma.entity.Localities;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.util.GeoFinder;
import alexiuscrow.diploma.util.HibernateUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ShopsDAOImpl implements ShopsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Shops> getAllShops() throws SQLException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	    List shops = null;
	    try{
	       tx = session.beginTransaction();
	       shops = session.createQuery("FROM Shops").list(); 
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return shops;
	}

	@Override
	public String getNearestShops(Double lat, Double lng, Integer radius)
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
			localities = pack(shops, lat, lng);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			try {
				result = ow.writeValueAsString(localities);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	       
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
	public String getAllLocalityShops(Double lat, Double lng)
			throws SQLException {
		
		String localityName = GeoFinder.getLocalityName(lat, lng);
		
		if (localityName == null)
			return null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Localities> localities = null;
		String result = null;
	    
	    try{
	       tx = session.beginTransaction();
	       Query query = session.createSQLQuery("call diplomadb.get_locality_shops_only(?,?,?);")
	    		   .addEntity(Shops.class)
	    		   .setString(0, lat.toString())
	    		   .setString(1, lng.toString())
	    		   .setString(2, localityName);
	       
			List<Shops> shops = query.list();
			localities = pack(shops, lat, lng);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			try {
				result = ow.writeValueAsString(localities);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	       
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
        return result;
	}
	
	private List<Localities> pack(List<Shops> shops, Double lat, Double lng){
		List<Localities> localities = new ArrayList<Localities>(0);
		
		Map<Integer, Set<Shops>> shopsAddedYet = new HashMap<Integer, Set	<Shops>>();
		
		for(Shops shop: shops){
	    	   shop.setDistance(GeoFinder.getDistance(lat, lng, shop)); // "@Transient" field
	    	   
	    	   Localities locality = shop.getLocality();
	    	   
	    	   if (!localities.contains(locality)){
	    		   localities.add(locality);
	    	   }
	    	   
	    	   if (!shopsAddedYet.containsKey(locality.getId())){
	    		   shopsAddedYet.put(locality.getId(), new LinkedHashSet<Shops>(0));
	    	   }
	    	   
	    	   shopsAddedYet.get(locality.getId()).add(shop);
	    }
		
		for(Localities locality: localities){
			locality.setShops(shopsAddedYet.get(locality.getId()));
		}
		
		return localities;	
	}

}
