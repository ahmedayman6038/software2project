package software2project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import software2project.services.storeService;

/**
 * This is a Store Controller Responsible for Handling Requests and Operations Related to Store Model 
 * @author Rick & morty
 *
 */
@RestController
public class storeRestController {
	@Autowired
	private storeService storeService;
	
	@PostMapping("/acceptStore/{storeId}")
	public boolean acceptStore(@PathVariable Integer storeId) {
		return storeService.acceptStore(storeId);
	}
}
