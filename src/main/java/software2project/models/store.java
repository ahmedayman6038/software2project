package software2project.models;

import java.util.ArrayList;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private String type;
	private boolean accepted;
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private user user;

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<storeProducts> products;
	
	@OneToMany(mappedBy="store")
    private Set<buyProducts> buyed;
    
	public store() {
		
	}
	public store(String name,String type ,user user,Set<storeProducts> products,boolean accepted) {
		super();
		this.name = name;
		this.user = user;
		this.products = products;
		this.accepted = accepted;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Set<storeProducts> getProducts() {
		return products;
	}
	public void setProducts(Set<storeProducts> products) {
		this.products = products;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
