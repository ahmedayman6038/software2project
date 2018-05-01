package software2project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software2project.models.storeProducts;
import software2project.repository.storeRepository;

/**
 * This is a Statistic Service Responsible For Business Logic Related to Statistc Controller 
 * @author Rick & morty
 *
 */

@Service
public class statisticService {
	@Autowired
	private storeRepository storeRepo;
	
	public Integer getViewedProduct(Integer storeId) {
		return storeRepo.getViewedProduct(storeId);
	}
	
	public Integer getProductsInStore(Integer storeId) {
		return storeRepo.getProductsInStore(storeId);
	}
	
	public Integer getBuyedProduct(Integer storeId) {
		return storeRepo.getBuyedProduct(storeId);
	}
	
	public  List<String> getSoldOutProduct(Integer storeId){
		List<storeProducts> products = storeRepo.getSoldOutProduct(storeId);
		List<String> names = new ArrayList<String>();
		for(int i=0;i<products.size();i++) {
			if(i==3) {
				break;
			}
			names.add(products.get(i).getProduct().getName());
		}
		return names;
	}
}
