package software2project.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software2project.models.*;
import software2project.repository.productRepository;
import software2project.repository.storeRepository;
import software2project.repository.userRepository;

/**
 * This is a Product Service Responsible For Business Logic Related to Product Controller 
 * @author Rick & morty
 *
 */

@Service
public class productService {
	@Autowired
	private userRepository userRepo;
	@Autowired
	private productRepository productRepo;
	@Autowired
	private storeRepository storeRepo;
	
	public float calculateTotalPrice(Float price,Integer quantity,String type,Integer uid) {
		float totalPrice = price * quantity;
		float disc = 0;
		if(type.equals("storeOwner")) {
			float per = (float) (15.0/100.0);
			disc += totalPrice*per;
		}
		List<buyProducts> buyed = userRepo.checkFirstBuy(uid);
		if(buyed.size() == 0) {
			float per = (float) (5.0/100.0);
			disc += totalPrice*per;
		}
		if(quantity >= 2) {
			float per = (float) (10.0/100.0);
			disc += totalPrice*per;
		}
		return totalPrice-disc;
	}
	
	public boolean addProductToStore(store store,product product,Float price,brand brand,Integer quantity) {
		if(price < product.getStartPrice() || price > product.getEndPrice()) {
			return false;
		}
		storeProducts storeProduct = new storeProducts();
		storeProduct.setStore(store);
		storeProduct.setProduct(product);
		storeProduct.setBrand(brand);
		storeProduct.setPrice(price);
		storeProduct.setQuantity(quantity);
		storeProduct.setUserViewed(0);
		store.getProducts().add(storeProduct);
		productRepo.save(product);
		storeRepo.save(store);
		return true;
	}
}
