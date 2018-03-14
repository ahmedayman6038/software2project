package software2project.models;

import java.util.Set;

import javax.persistence.Entity;

/**
 * This is a Normal Product Model Class Extends From Product Model Class
 * @author Rick & morty
 *
 */
@Entity
public class normalProduct extends product{

	public normalProduct() {
		super();
	}

	public normalProduct(String name, float startPrice, float endPrice, String category,brand brand, Set<storeProducts> stores) {
		super(name, startPrice, endPrice, category, brand, stores);
	}

}
