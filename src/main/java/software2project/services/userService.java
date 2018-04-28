package software2project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software2project.models.user;
import software2project.repository.userRepository;

@Service
public class userService {
	@Autowired
	private userRepository userRepo;
	
	public user login(user user) {
		user u = userRepo.findUser(user.getEmail(), user.getPassword());
		return u;
	}
	
	public boolean register(user user) {
		user u = userRepo.findByEmail(user.getEmail());
		if(u == null) {
			userRepo.save(user);
			return true;
		}
		return false;
	}
}
