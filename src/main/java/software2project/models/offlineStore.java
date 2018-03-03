package software2project.models;

import java.util.Set;

import javax.persistence.Entity;

/**
 * This is a OfflineStore Model Class Extends From Store Model Class
 * @author Rick & morty
 *
 */
@Entity
public class offlineStore extends store {
	private String address;

	public offlineStore() {
		
	}
	public offlineStore(String name, Set<product> products, String address) {
		super(name,products);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

}
