package alexiuscrow.diploma.dao;

import alexiuscrow.diploma.dao.impl.DiscountsDAOImpl;
import alexiuscrow.diploma.dao.impl.LocalitiesDAOImpl;
import alexiuscrow.diploma.dao.impl.ShopsDAOImpl;

public class Factory {
	private static Factory factory = null;
	
	private static ShopsDAO shops= null;	
	
	private static DiscountsDAO discounts= null;	
	
	private static LocalitiesDAO localities= null;
	
	public static synchronized Factory getInstance(){
		if(factory == null)
			factory = new Factory();
		
		return factory;
	}
	
	public ShopsDAO getShops(){
		if(shops == null)
			shops = new ShopsDAOImpl();
		
		return shops;
	}
	
	public DiscountsDAO getDiscounts(){
		if(discounts == null)
			discounts = new DiscountsDAOImpl();
		
		return discounts;
	}
	
	public LocalitiesDAO getLicalities(){
		if(localities == null)
			localities = new LocalitiesDAOImpl();
		
		return localities;
	}
}
