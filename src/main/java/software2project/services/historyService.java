package software2project.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software2project.models.brand;
import software2project.models.history;
import software2project.models.product;
import software2project.models.store;
import software2project.models.storeProducts;
import software2project.models.user;
import software2project.repository.historyRepository;
import software2project.repository.userRepository;

/**
 * This is a History Service Responsible For Business Logic Related to History Controller 
 * @author Rick & morty
 *
 */

@Service
public class historyService {
	@Autowired
	private historyRepository historyRepo;
	@Autowired
	private userRepository userRepo;
	
	public boolean addHistory(product product,store store,String email,String type,brand brand,Float price,Integer quantity)
	{
		user user = userRepo.findByEmail(email);
		if(user != null)
		{
			history history = new history();
			history.setDate(new Date());
			history.setBrandId(brand.getId());
			history.setProductName(product.getName());
			history.setPrice(price);
			history.setProductId(product.getId());
			history.setStoreId(store.getId());
			history.setQuantity(quantity);
			history.setUser(user);
			history.setUserViewed(0);
			history.setType("addProduct");
			historyRepo.save(history);
			return true;
		}
		return false;
	}
	
	public boolean addHistory(storeProducts sproduct,String email,String type)
	{
		user user = userRepo.findByEmail(email);
		if(user != null)
		{
			history history = new history();
			history.setDate(new Date());
			history.setBrandId(sproduct.getBrand().getId());
			history.setProductName(sproduct.getProduct().getName());
			history.setPrice(sproduct.getPrice());
			history.setProductId(sproduct.getProduct().getId());
			history.setStoreId(sproduct.getStore().getId());
			history.setQuantity(sproduct.getQuantity());
			history.setOffer(sproduct.getOffer());
			history.setUserViewed(sproduct.getUserViewed());
			history.setLastBuyedDate(sproduct.getLastBuyedDate());
			history.setUser(user);
			history.setType(type);
			historyRepo.save(history);
			return true;
		}
		return false;
	}
}
