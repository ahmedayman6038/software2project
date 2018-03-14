package software2project.models;

import java.util.Set;

import javax.persistence.Entity;

/**
 * This is a OfflineStore Model Class Extends From Store Model Class
 * @author Rick & morty
 *
 */
@Entity
public class normalStore extends store {
	private String address;

	public normalStore() {
		super();
	}
	public normalStore(String name,String type,user user,Set<storeProducts> products, String address, boolean accepted) {
		super(name,type ,user,products,accepted);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

}
