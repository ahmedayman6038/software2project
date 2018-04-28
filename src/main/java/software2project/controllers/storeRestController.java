package software2project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import software2project.services.storeService;

@RestController
public class storeRestController {
	@Autowired
	private storeService storeService;
	
	@PostMapping("/acceptStore/{storeId}")
	public boolean acceptStore(@PathVariable Integer storeId) {
		return storeService.acceptStore(storeId);
	}
}
