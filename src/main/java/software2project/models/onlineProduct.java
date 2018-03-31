package software2project.models;

import java.util.Set;

import javax.persistence.Entity;

/**
 * This is a Online Product Model Class Extends From Product Model Class
 * @author Rick & morty
 *
 */
@Entity
public class onlineProduct extends product{

	public onlineProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public onlineProduct(String name, float startPrice, float endPrice, String category, Set<storeProducts> stores,Set<buyProducts> users) {
		super(name, startPrice, endPrice, category, stores,users);
		// TODO Auto-generated constructor stub
	}

}
