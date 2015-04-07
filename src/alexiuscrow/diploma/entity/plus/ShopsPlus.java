package alexiuscrow.diploma.entity.plus;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import alexiuscrow.diploma.entity.Localities;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.util.GeoFinder;

@XmlRootElement
public class ShopsPlus{
	
	private Shops shop;
	private Localities locality;
	
	public ShopsPlus(){}

	public ShopsPlus(Shops shop, Localities locality) {
		this.shop = shop;
		this.locality = locality;
	}
	
	public static ArrayList<ShopsPlus> eatNourishing(List<Shops> shops, Double lat, Double lng, Session session){
		ArrayList<ShopsPlus> shopsPlus = new ArrayList<ShopsPlus>();
		ShopsPlus shopPlus = null;
		
		for(Shops shop: shops){
			shop.setDistance(GeoFinder.getDistance(lat, lng, shop));
			
			Criteria cr = session.createCriteria(Localities.class);
			cr.add(Restrictions.eq("id", shop.getLocalityId()));
			Localities locality = (Localities) cr.uniqueResult();
			
			shopPlus = new ShopsPlus();
			shopPlus.setShop(shop);
			shopPlus.setLocality(locality);
			
			shopsPlus.add(shopPlus);
		}
		return shopsPlus;
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


