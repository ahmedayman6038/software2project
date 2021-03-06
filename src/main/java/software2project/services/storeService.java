package software2project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software2project.models.store;
import software2project.repository.storeRepository;

/**
 * This is a Store Service Responsible For Business Logic Related to Store Controller 
 * @author Rick & morty
 *
 */

@Service
public class storeService {
	@Autowired
	private storeRepository storeRepo;
	
	public boolean acceptStore(Integer storeId) {
		store s = storeRepo.findOne(storeId);
		if(s != null) {
			s.setAccepted(true);
			storeRepo.save(s);
			return true;
		}
		return false;
	}

}
