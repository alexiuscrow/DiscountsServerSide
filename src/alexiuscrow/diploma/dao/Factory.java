package alexiuscrow.diploma.dao;

import alexiuscrow.diploma.dao.impl.DiscountsDAOImpl;
import alexiuscrow.diploma.dao.impl.LocalitiesDAOImpl;
import alexiuscrow.diploma.dao.impl.ShopsDAOImpl;
import alexiuscrow.diploma.dao.plus.DiscountsPlusDAO;
import alexiuscrow.diploma.dao.plus.ShopsPlusDAO;
import alexiuscrow.diploma.dao.plus.impl.DiscountsPlusDAOImpl;
import alexiuscrow.diploma.dao.plus.impl.ShopsPlusDAOImpl;

public class Factory {
	private static Factory factory = null;
	
	private static ShopsDAO shops= null;
	private static ShopsPlusDAO shopsPlus= null;	
	
	private static DiscountsDAO discounts= null;	
	private static DiscountsPlusDAO discountsPlus= null;	
	
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
	
	public ShopsPlusDAO getShopsPlus(){
		if(shopsPlus == null)
			shopsPlus = new ShopsPlusDAOImpl();
		
		return shopsPlus;
	}
	
	public DiscountsDAO getDiscounts(){
		if(discounts == null)
			discounts = new DiscountsDAOImpl();
		
		return discounts;
	}
	
	public DiscountsPlusDAO getDiscountsPlus(){
		if(discountsPlus == null)
			discountsPlus = new DiscountsPlusDAOImpl();
		
		return discountsPlus;
	}
	
	public LocalitiesDAO getLicalities(){
		if(localities == null)
			localities = new LocalitiesDAOImpl();
		
		return localities;
	}
}
