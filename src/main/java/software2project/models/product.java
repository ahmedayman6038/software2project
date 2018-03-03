package software2project.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	private float price;
	private String category;
	@ManyToOne
	@JoinColumn(name="brand_id", nullable=false)
	private brand brand;
	private String type;
	@ManyToMany(mappedBy = "products")
    private Set<store> stores;
    
	public product() {
		
	}
	public product(String name, float price, String category, brand brand, String type, Set<store> stores ) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.brand = brand;
		this.type = type;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<store> getStores() {
		return stores;
	}
	public void setStores(Set<store> stores) {
		this.stores = stores;
	}
}
