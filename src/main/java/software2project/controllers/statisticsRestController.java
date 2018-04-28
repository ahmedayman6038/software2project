package software2project.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software2project.services.statisticService;

@RestController
public class statisticsRestController {
	@Autowired
	private statisticService statisticService;
	
	@GetMapping("/getViewed/{id}")
	public Integer getViewedProduct(@PathVariable Integer id) {
		return statisticService.getViewedProduct(id);
	}
	
	@GetMapping("/getProductsNumber/{id}")
	public Integer getProductsNumber(@PathVariable Integer id) {
		return statisticService.getProductsInStore(id);
	}
	
	@GetMapping("/getSoldOut/{id}")
	public List<String> getSoldOutProduct(@PathVariable Integer id) {
		return statisticService.getSoldOutProduct(id);
	}
	
	@GetMapping("/getBuyed/{id}")
	public Integer getBuyedProduct(@PathVariable Integer id) {
		return statisticService.getBuyedProduct(id);
	}
}
