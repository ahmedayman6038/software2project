package software2project.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This is a History Model Class
 * @author Rick & morty
 *
 */
@Entity
public class history {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private user user;
	private Integer storeId;
	private Integer productId;
	private String productName;
	private Integer brandId;
	private Float price;
	private Integer quantity;
	private Integer offer;
	private String type;
	private Date date;
	private Date lastBuyedDate;
	private Integer userViewed;
	
	public history() {
		
	}
	public history(Integer id, Integer storeId,String productName, Integer productId, Integer brandId, Float price, Integer quantity,
			Integer offer,String type ,Date date) {
		super();
		this.id = id;
		this.storeId = storeId;
		this.productId = productId;
		this.productName = productName;
		this.brandId = brandId;
		this.price = price;
		this.quantity = quantity;
		this.offer = offer;
		this.type = type;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOffer() {
		return offer;
	}
	public void setOffer(Integer offer) {
		this.offer = offer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getLastBuyedDate() {
		return lastBuyedDate;
	}
	public void setLastBuyedDate(Date lastBuyedDate) {
		this.lastBuyedDate = lastBuyedDate;
	}
	public Integer getUserViewed() {
		return userViewed;
	}
	public void setUserViewed(Integer userViewed) {
		this.userViewed = userViewed;
	}
}
