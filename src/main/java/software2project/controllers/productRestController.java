package software2project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import software2project.models.buyProducts;
import software2project.repository.userRepository;

@RestController
public class productRestController {
	@Autowired
	private userRepository userRepo;
	@GetMapping("/calculate/{price}/{quantity}")
	public float calculateTotalPrice(HttpServletRequest request,@PathVariable Float price,@PathVariable Integer quantity) {
		String type = (String) request.getSession().getAttribute("type");
		Integer id = (Integer) request.getSession().getAttribute("id");
		float totalPrice = price * quantity;
		float disc = 0;
		if(type.equals("storeOwner")) {
			float per = (float) (15.0/100.0);
			disc += totalPrice*per;
			System.out.println("a " + disc);
		}
		List<buyProducts> buyed = userRepo.checkFirstBuy(id);
		if(buyed.size() == 0) {
			float per = (float) (5.0/100.0);
			disc += totalPrice*per;
			System.out.println("b "+disc);
		}
		if(quantity >= 2) {
			float per = (float) (10.0/100.0);
			disc += totalPrice*per;
			System.out.println("c "+disc);
		}
		return totalPrice-disc;
	}
}
