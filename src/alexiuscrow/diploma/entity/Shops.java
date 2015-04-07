package alexiuscrow.diploma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import alexiuscrow.diploma.entity.enums.Categories;

@XmlRootElement
@Entity
@Table(name = "shops")
public class Shops {
	@Id
	@GeneratedValue
	@Column(name="id")
	protected Integer id;
	
	@Column(name="name", length=45)
	protected String name;
	
	@Column(name="category", columnDefinition="ENUM('undefined','auto','children_prod','food','game','book','electronics','beuty_and_health','fashion','footwear','clothing','sports','homewere','pet_prod','services','gift_and_flowers')")
	@Enumerated(EnumType.STRING)
	protected Categories category;
	
	@Column(name="latitude")
	protected Double latitude;
	
	@Column(name="longitude")
	protected Double longitude;
	
	@Column(name="locality_id")
	protected Integer localityId;
	
	@Column(name="address", length=80)
	protected String address;
	
	public Shops() {
	}
	
	public Shops(Integer id, String name, Categories category, Double latitude,
			Double longitude, Integer localityId, String address) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.localityId = localityId;
		this.address = address;
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

	@Override
	public String toString() {
		return String
				.format("Shops [id=%s, name=%s, category=%s, latitude=%s, longitude=%s, localityId=%s, address=%s]",
						id, name, category, latitude, longitude, localityId,
						address);
	}


}
