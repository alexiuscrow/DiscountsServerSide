package alexiuscrow.diploma.entity;

import java.util.Date;

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
import com.google.gson.annotations.Expose;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Discounts", description = "Discounts resource representation" )
@XmlRootElement
@Entity
@Table(name="discounts")
public class Discounts {
	@ApiModelProperty( value = "Discounts's identifier", required = true )
	@Expose
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@ApiModelProperty( value = "Discounts's title", required = true )
	@Expose
	@Column(name="title")
	private String title;
	
	@ApiModelProperty( value = "Discounts's start date", required = true )
	@Expose
	@Column(name="start_date")
	@Temporal(TemporalType.DATE) 
	private Date startDate;
	
	@ApiModelProperty( value = "Discounts's end date", required = true )
	@Expose
	@Column(name="end_date")
	@Temporal(TemporalType.DATE) 
	private Date endDate;
	
	@ApiModelProperty( value = "Discounts's description", required = true )
	@Expose
	@Column(name="description")
	private String description;
	
	@ApiModelProperty( value = "Discounts's image name", required = true )
	@Expose
	@Column(name="image")
	private String imageUrl;
	
	@ApiModelProperty(hidden = true)
	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName="id" ,nullable = false)
	@JsonBackReference
	private Shops shop;

	public Discounts() {
	}

	public Discounts(Integer id, String title, Date startDate, Date endDate,
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
