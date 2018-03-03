package software2project.models;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * This is a Store Model Class
 * @author Rick & morty 
 *
 */
@Entity
public class store {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
	private Set<product> products;
	public store() {
		
	}
	public store(String name, Set<product> products) {
		super();
		this.name = name;
		this.products = products;
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
	public ArrayList<product> showProduct(){
		return null;
	}
	public Set<product> getProducts() {
		return products;
	}
	public void setProducts(Set<product> products) {
		this.products = products;
	}
}
