package software2project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import software2project.models.store;
import software2project.repository.storeRepository;

@RestController
public class storeRestController {
	@Autowired
	private storeRepository storeRepo;
	
	@PostMapping("/acceptStore/{storeId}")
	public Integer acceptStore(@PathVariable Integer storeId) {
		store s = storeRepo.findOne(storeId);
		s.setAccepted(true);
		storeRepo.save(s);
		return s.getId();
	}
}
