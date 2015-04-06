package alexiuscrow.diploma.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import alexiuscrow.diploma.dao.DiscountsDAO;
import alexiuscrow.diploma.entity.Discounts;
import alexiuscrow.diploma.util.HibernateUtil;

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

}
