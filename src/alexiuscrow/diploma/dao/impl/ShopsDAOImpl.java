package alexiuscrow.diploma.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.ShopsDAO;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.util.HibernateUtil;

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



}
