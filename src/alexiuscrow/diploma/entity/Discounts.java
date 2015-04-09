package alexiuscrow.diploma.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@XmlRootElement
@Entity
@Table(name="discounts")
public class Discounts {
	
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
	
	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName="id" ,nullable = false)
	@JsonBackReference
	private Shops shop;

	public Discounts() {
	}

	public Discounts(Integer id, String title, Calendar startDate, Calendar endDate,
			String description, String imageUrl, Shops shop) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.imageUrl = imageUrl;
		this.shop = shop;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Shops getShop() {
		return shop;
	}

	public void setShop(Shops shop) {
		this.shop = shop;
	}

	@Override
	public String toString() {
		return String
				.format("Discounts [id=%s, title=%s, startDate=%s, endDate=%s, description=%s, imageUrl=%s]",
						id, title, startDate, endDate, description, imageUrl);
	}
	
}
