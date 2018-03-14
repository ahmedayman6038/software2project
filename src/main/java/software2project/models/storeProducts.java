package software2project.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class storeProducts implements Serializable {
	@Id
    @ManyToOne
    @JoinColumn(name = "store_id")
	private store store;
	@Id
    @ManyToOne
    @JoinColumn(name = "product_id")
	private product product;
	private Float price;
	private Integer userBuyed;
	private Integer userViewed;
	private Integer quantity;
	private Date lastBuyedDate;
	
	public storeProducts() {
		
	}
	public storeProducts(store store, product product, Float price,	Integer userBuyed, Integer userViewed,Integer quantity, Date lastBuyedDate) {
		super();
		this.store = store;
		this.product = product;
		this.price = price;
		this.userBuyed = userBuyed;
		this.userViewed = userViewed;
		this.quantity = quantity;
		this.lastBuyedDate = lastBuyedDate;
	}
	public store getStore() {
		return store;
	}
	public void setStore(store store) {
		this.store = store;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getUserBuyed() {
		return userBuyed;
	}
	public void setUserBuyed(Integer userBuyed) {
		this.userBuyed = userBuyed;
	}
	public Integer getUserViewed() {
		return userViewed;
	}
	public void setUserViewed(Integer userViewed) {
		this.userViewed = userViewed;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getLastBuyedDate() {
		return lastBuyedDate;
	}
	public void setLastBuyedDate(Date lastBuyedDate) {
		this.lastBuyedDate = lastBuyedDate;
	}
}
