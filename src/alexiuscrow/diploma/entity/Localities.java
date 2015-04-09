package alexiuscrow.diploma.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@XmlRootElement
@Entity
@Table(name = "localities")
public class Localities {
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name",nullable=false, length=57)
	private String name;
	
	@OneToMany(mappedBy = "locality")
	@JsonManagedReference
	private Set<Shops> shops = new HashSet<Shops>(0);
	
	public Localities() {
	}
	
	public Localities(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Localities(Integer id, String name, Set<Shops> shops) {
		this.id = id;
		this.name = name;
		this.shops = shops;
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

	public Set<Shops> getShops() {
		return shops;
	}

	public void setShops(Set<Shops> shops) {
		this.shops = shops;
	}

	@Override
	public String toString() {
		return String.format("Localities [id=%s, name=%s, shops=%s]", id, name,
				shops);
	}
}
