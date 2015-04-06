package alexiuscrow.diploma.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.LocalitiesDAO;
import alexiuscrow.diploma.entity.Localities;
import alexiuscrow.diploma.util.HibernateUtil;

public class LocalitiesDAOImpl implements LocalitiesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Localities> getAllLocalities() throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	    List localities = null;
	    try{
	       tx = session.beginTransaction();
	       localities = session.createQuery("FROM Localities").list(); 
	       tx.commit();
	    }catch (HibernateException e) {
	       if (tx!=null) tx.rollback();
	       e.printStackTrace(); 
	    }finally {
	       session.close(); 
	    }
		return localities;
	}

}
