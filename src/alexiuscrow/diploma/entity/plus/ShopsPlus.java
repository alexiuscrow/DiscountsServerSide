package alexiuscrow.diploma.entity.plus;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import alexiuscrow.diploma.entity.Localities;
import alexiuscrow.diploma.entity.Shops;
import alexiuscrow.diploma.entity.enums.Categories;
import alexiuscrow.diploma.util.GeoFinder;

@XmlRootElement
public class ShopsPlus{
	

	protected Integer id;
	protected String name;
	protected Categories category;
	protected Double latitude;
	protected Double longitude;
	protected Integer localityId;
	protected String address;
	private String localityName;
	private Double distance;
	
	public ShopsPlus(){}
	
	public ShopsPlus(Integer id, String name, Categories category, Double latitude,
			Double longitude, Integer localityId, String address,
			String localityName, Double distance) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.localityId = localityId;
		this.address = address;
		this.localityName = localityName;
		this.distance = distance;
	}

	public void eatUp(Shops shop){
		this.id = shop.getId();
		this.name = shop.getName();
		this.category = shop.getCategory();
		this.latitude = shop.getLatitude();
		this.longitude = shop.getLongitude();
		this.localityId = shop.getLocalityId();
		this.address = shop.getAddress();
	}
	
	public static ShopsPlus eatUp(Shops fromShop, ShopsPlus toShop){
		toShop.setId(fromShop.getId());
		toShop.setName(fromShop.getName());
		toShop.setCategory(fromShop.getCategory());
		toShop.setLatitude(fromShop.getLatitude());
		toShop.setLongitude(fromShop.getLongitude());
		toShop.setLocalityId(fromShop.getLocalityId());
		toShop.setAddress(fromShop.getAddress());

		return toShop;
	}
	
	public static ArrayList<ShopsPlus> eatUp(List<Shops> shops){
		ArrayList<ShopsPlus> shopsPlus = new ArrayList<ShopsPlus>();
		ShopsPlus shopPlus = new ShopsPlus();
		
		for(Shops shop: shops){
			shopPlus = eatUp(shop, shopPlus);
			shopsPlus.add(shopPlus);
		}
		
		return shopsPlus;
	}
	
	public static ArrayList<ShopsPlus> eatNourishing(List<Shops> shops, Double lat, Double lng, Session session){
		ArrayList<ShopsPlus> shopsPlus = new ArrayList<ShopsPlus>();
		ShopsPlus shopPlus = null;
		
//		Criteria cr  = session.createCriteria(Localities.class);
		Localities locality = null;
		
		for(Shops shop: shops){
			shopPlus = new ShopsPlus();
			shopPlus = eatUp(shop, shopPlus);
			shopPlus.distance = GeoFinder.getDistance(lat, lng, shop);
			Criteria cr = session.createCriteria(Localities.class);
			System.out.println(shopPlus);
			cr.add(Restrictions.eq("id", shop.getId()));
			locality = (Localities) cr.uniqueResult();
			System.out.println(locality.getName());
			shopPlus.setLocalityName(locality.getName());
			shopsPlus.add(shopPlus);
		}
		return shopsPlus;
	}
	
	
	public void eatUp(Localities locality){
		this.localityName = locality.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getLocalityId() {
		return localityId;
	}

	public void setLocalityId(Integer localityId) {
		this.localityId = localityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return String
				.format("ShopsPlus [id=%s, name=%s, category=%s, latitude=%s, longitude=%s, localityId=%s, address=%s, localityName=%s, distance=%s]",
						id, name, category, latitude, longitude, localityId,
						address, localityName, distance);
	}
}


