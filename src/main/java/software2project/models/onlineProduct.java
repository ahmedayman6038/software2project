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

	public onlineProduct(String name, float startPrice, float endPrice, String category,brand brand, Set<storeProducts> stores) {
		super(name, startPrice, endPrice, category, brand, stores);
		// TODO Auto-generated constructor stub
	}

}
