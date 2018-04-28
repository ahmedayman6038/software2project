package software2project.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import software2project.Main;
import software2project.models.brand;
import software2project.models.user;
import software2project.repository.brandRepository;
import software2project.repository.userRepository;

/**
 * This is a Brand Controller Responsible for Handling Requests and Operations Related to Brand Model 
 * @author Rick & morty
 *
 */
@Controller
public class brandController {
	@Autowired
	private brandRepository brandRepo;
	@Autowired
	private userRepository userRepo;
	
	@GetMapping("/addBrand")
	public String addProduct(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "admin");
		if(user != null) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("brand", new brand());
			return "addBrand";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/addBrand")
	public String addProduct(@ModelAttribute brand brand) {
		brandRepo.save(brand);
		return "redirect:/addBrand";
	}
}
