package software2project.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This is a Product Model Class
 * @author Rick & morty
 *
 */
@Entity
public class product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private float startPrice;
	private float endPrice;
	private String category;
	@ManyToOne
	@JoinColumn(name="brand_id", nullable=false)
	private brand brand;
	@OneToMany(mappedBy = "product")
	private Set<storeProducts> stores;
    
	public product() {
		
	}
	public product(String name, float startPrice, float endPrice, String category, brand brand, Set<storeProducts> stores ) {
		super();
		this.name = name;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.category = category;
		this.brand = brand;
		this.stores = stores;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(float startPrice) {
		this.startPrice = startPrice;
	}
	public float getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(float endPrice) {
		this.endPrice = endPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public brand getBrand() {
		return brand;
	}
	public void setBrand(brand brand) {
		this.brand = brand;
	}
	public Set<storeProducts> getStores() {
		return stores;
	}
	public void setStores(Set<storeProducts> stores) {
		this.stores = stores;
	}
}
