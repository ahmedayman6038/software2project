package software2project.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import software2project.models.storeProducts;
import software2project.models.store;
import software2project.repository.storeRepository;

/**
 * This is a Services Controller Responsible for Handling Rest Requests and Operations Related to
 * Services used in the project   
 * @author Rick & morty
 *
 */
@RestController
public class servicesController {
	@Autowired
	private storeRepository storeRepo;
	
	@GetMapping("/getViewed/{id}")
	public Integer getViewedProduct(@PathVariable Integer id) {
		return storeRepo.getViewedProduct(id);
	}
	
	@GetMapping("/getSoldOut/{id}")
	public List<String> getSoldOutProduct(@PathVariable Integer id) {
		List<storeProducts> products = storeRepo.getSoldOutProduct(id);
		List<String> names = new ArrayList<String>();
		for(int i=0;i<products.size();i++) {
			if(i==3) {
				break;
			}
			names.add(products.get(i).getProduct().getName());
		}
		return names;

	}

	@GetMapping("/getBuyed/{id}")
	public Integer getBuyedProduct(@PathVariable Integer id) {
		return storeRepo.getBuyedProduct(id);
	}
	
	@PostMapping("/acceptStore/{storeId}")
	public Integer acceptStore(@PathVariable Integer storeId) {
		store s = storeRepo.findOne(storeId);
		s.setAccepted(true);
		storeRepo.save(s);
		return s.getId();
	}
}
