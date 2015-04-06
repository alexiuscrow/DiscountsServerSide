package alexiuscrow.diploma.entity.plus;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Immutable;

@XmlRootElement
@Entity
@Immutable
public class DiscountsPlus {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="start_date")
	@Temporal(TemporalType.DATE) 
	private Calendar startDate;
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE) 
	private Calendar endDate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String imageUrl;
	
	@Column(name="shop_name", insertable=false, updatable=false)
	private String shopName;
	
	@Column(name="shop_id")
	private Integer shopId;
	
	@Column(name="locality_name", insertable=false, updatable=false)
	private String localityName;
	
	@Column(name="address", insertable=false, updatable=false)
	private String address;
	
	@Column(name="distance", insertable=false, updatable=false)
	private Double distance;
	
	public DiscountsPlus() {
	}

	public DiscountsPlus(int id, String title, String description, Calendar startDate,
			Calendar endDate, String imageUrl, String shopName, int shopId,
			String localityName, String address, Double distance) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.imageUrl = imageUrl;
		this.shopName = shopName;
		this.shopId = shopId;
		this.localityName = localityName;
		this.address = address;
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getDistace() {
		return distance;
	}

	public void setDistace(Double distace) {
		this.distance = distace;
	}

	@Override
	public String toString() {
		return String
				.format("DiscountsPlus [id=%s, title=%s, startDate=%s, endDate=%s, description=%s, imageUrl=%s, shopName=%s, shopId=%s, localityName=%s, address=%s, distance=%s]",
						id, title, startDate, endDate, description, imageUrl,
						shopName, shopId, localityName, address, distance);
	}

	
	
}
