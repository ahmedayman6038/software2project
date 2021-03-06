package software2project.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@OneToMany(mappedBy = "product")
	private Set<storeProducts> stores;
    
	@OneToMany(mappedBy = "product")
	private Set<buyProducts> users;
	
	public product() {
		
	}
	public product(String name, float startPrice, float endPrice, String category, Set<storeProducts> stores,Set<buyProducts> users ) {
		super();
		this.name = name;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.category = category;
		this.stores = stores;
		this.users = users;
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
	public Set<storeProducts> getStores() {
		return stores;
	}
	public void setStores(Set<storeProducts> stores) {
		this.stores = stores;
	}
	public Set<buyProducts> getUsers() {
		return users;
	}
	public void setUsers(Set<buyProducts> users) {
		this.users = users;
	}
}
