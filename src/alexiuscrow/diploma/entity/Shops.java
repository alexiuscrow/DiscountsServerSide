package alexiuscrow.diploma.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import alexiuscrow.diploma.entity.enums.Categories;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Shops", description = "Shops resource representation" )
@XmlRootElement
@Entity
@Table(name = "shops")
public class Shops {
	@ApiModelProperty( value = "Shop's identifier", required = true )
	@Expose
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	protected Integer id;
	
	@ApiModelProperty(value = "Shop's name", required = true )
	@Expose
	@Column(name="name", length=45, nullable=false)
	protected String name;
	
	@ApiModelProperty(value = "Shop's category", required = true )
	@Expose
	@Column(name="category", columnDefinition="ENUM('undefined','auto','children_prod','food','game','book','electronics','beuty_and_health','fashion','footwear','clothing','sports','homewere','pet_prod','services','gift_and_flowers')",
			nullable=false)
	@Enumerated(EnumType.STRING)
	protected Categories category;
	
	@ApiModelProperty(value = "Shop's latitude", required = true )
	@Expose
	@Column(name="latitude", nullable=false)
	protected Double latitude;
	
	@ApiModelProperty(value = "Shop's longitude", required = true )
	@Expose
	@Column(name="longitude",nullable = false)
	protected Double longitude;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne
	@JoinColumn(name = "locality_id", referencedColumnName="id")
	@JsonBackReference
	protected Localities locality;
	
	@ApiModelProperty(value = "Shop's address", required = true )
	@Expose
	@Column(name="address", length=80, nullable=false)
	protected String address;
	
//	@Expose
	@ApiModelProperty(value = "Disctance to shop", required = true )
	@Transient
	private Double distance;
	
	@ApiModelProperty(value = "Shop's discounts set", required = true )
	@Expose
	@OneToMany( mappedBy = "shop")
	@JsonManagedReference
	private Set<Discounts> discounts = new HashSet<Discounts>(0);
	
	public Shops() {}
	
	public Shops(Integer id, String name, Categories category, Double latitude,
			Double longitude, Localities locality, String address) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locality = locality;
		this.address = address;
	}
	

	public Shops(Integer id, String name, Categories category, Double latitude,
			Double longitude, Localities locality, String address,
			Double distance, Set<Discounts> discounts) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locality = locality;
		this.address = address;
		this.distance = distance;
		this.discounts = discounts;
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

	public Localities getLocality() {
		return locality;
	}

	public void setLocality(Localities locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Set<Discounts> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Set<Discounts> discounts) {
		this.discounts = discounts;
	}

	@Override
	public String toString() {
		return String
				.format("Shops [id=%s, name=%s, category=%s, latitude=%s, longitude=%s, address=%s, distance=%s, discounts=%s]",
						id, name, category, latitude, longitude,
						address, distance, discounts);
	}


}
