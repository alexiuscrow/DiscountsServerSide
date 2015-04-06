package alexiuscrow.diploma.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	@Column(name="shop_id")
	private Integer shopId;

	public Discounts() {
	}

	public Discounts(Integer id, String title, Calendar startDate, Calendar endDate,
			String description, String imageUrl, Integer shopId) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.imageUrl = imageUrl;
		this.shopId = shopId;
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return String
				.format("Discounts [id=%s, title=%s, startDate=%s, endDate=%s, description=%s, imageUrl=%s, shopId=%s]",
						id, title, startDate, endDate, description, imageUrl,
						shopId);
	}
	
}
