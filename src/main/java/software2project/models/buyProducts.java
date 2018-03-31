package software2project.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class buyProducts implements Serializable{
	@Id
    @ManyToOne
    @JoinColumn(name = "user_id")
	private user user;
	@Id
    @ManyToOne
    @JoinColumn(name = "product_id")
	private product product;
	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	private store store;
	private String address;
	
	public buyProducts() {
		
	}
	public buyProducts(user user, product product, store store,String address) {
		super();
		this.user = user;
		this.product = product;
		this.address = address;
		this.store = store;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public store getStore() {
		return store;
	}
	public void setStore(store store) {
		this.store = store;
	}
}
