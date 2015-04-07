package alexiuscrow.diploma.entity.plus;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import alexiuscrow.diploma.entity.Discounts;
import alexiuscrow.diploma.entity.Localities;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.util.GeoFinder;

@XmlRootElement
public class DiscountsPlus {
	
	private Discounts discount;
	private Shops shop;
	private Localities locality;
	
	public DiscountsPlus() {
	}

	public DiscountsPlus(Discounts discount, Shops shop, Localities locality) {
		this.discount = discount;
		this.shop = shop;
		this.locality = locality;
	}

	public static ArrayList<DiscountsPlus> eatNourishing(List<Shops> shops, Double lat, Double lng, Session session){
		ArrayList<DiscountsPlus> discountsPlus = new ArrayList<DiscountsPlus>();
		DiscountsPlus discountPlus = null;
		
		for (Shops shop: shops){
			shop.setDistance(GeoFinder.getDistance(lat, lng, shop));
			
			Criteria cr = session.createCriteria(Localities.class);
			cr.add(Restrictions.eq("id", shop.getLocalityId()));
			Localities locality = (Localities) cr.uniqueResult();
			
			cr = session.createCriteria(Discounts.class);
			cr.add(Restrictions.eq("shopId", shop.getId()));
			List<Discounts> discounts = cr.list();
			
			for (Discounts discount: discounts){
				discountPlus = new DiscountsPlus();
				discountPlus.shop = shop;
				discountPlus.locality = locality;
				discountPlus.discount = discount;
				
				discountsPlus.add(discountPlus);
			}
			
		}
		return discountsPlus;
	}

	public Discounts getDiscount() {
		return discount;
	}

	public void setDiscount(Discounts discounts) {
		this.discount = discounts;
	}

	public Shops getShop() {
		return shop;
	}

	public void setShop(Shops shop) {
		this.shop = shop;
	}

	public Localities getLocality() {
		return locality;
	}

	public void setLocality(Localities locality) {
		this.locality = locality;
	}
	
}
