package software2project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software2project.models.history;
import software2project.repository.historyRepository;

@Service
public class historyService {
	@Autowired
	private historyRepository historyRepo;
	
	public int getStoreHistory(Integer sid){
		return historyRepo.getStoreHistory(sid).size();
	}
	
	public history getHistory(Integer sid) {
		return historyRepo.getHistory(sid);
	}
}
