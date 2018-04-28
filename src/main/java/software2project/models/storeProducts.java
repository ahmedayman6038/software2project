package software2project.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



/**
 * This is a StoreProducts Model Class
 * @author Rick & morty
 *
 */
@Entity
public class storeProducts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2892669951818837233L;
	@Id
    @ManyToOne
    @JoinColumn(name = "store_id")
	private store store;
	@Id
    @ManyToOne
    @JoinColumn(name = "product_id")
	private product product;
	@ManyToOne
	@JoinColumn(name="brand_id", nullable=false)
	private brand brand;
	private Float price;
	private Integer userViewed;
	private Integer quantity;
	private Integer offer;
	private Date lastBuyedDate;
	
	public storeProducts() {
		
	}
	public storeProducts(store store, product product,brand brand,Float price, Integer userViewed,Integer quantity, Integer offer,Date lastBuyedDate) {
		super();
		this.store = store;
		this.product = product;
		this.brand = brand;
		this.price = price;
		this.userViewed = userViewed;
		this.quantity = quantity;
		this.offer = offer;
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
	public brand getBrand() {
		return brand;
	}
	public void setBrand(brand brand) {
		this.brand = brand;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getOffer() {
		return offer;
	}
	public void setOffer(Integer offer) {
		this.offer = offer;
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
