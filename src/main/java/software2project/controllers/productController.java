package software2project.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import software2project.Main;
import software2project.models.normalProduct;
import software2project.models.onlineProduct;
import software2project.models.user;
import software2project.repository.brandRepository;
import software2project.repository.productRepository;
import software2project.repository.userRepository;

/**
 * This is a Product Controller Responsible for Handling Requests and Operations Related to Product Model 
 * @author Rick & morty
 *
 */
@Controller
public class productController {
	
	@Autowired
	private brandRepository brandRepo;
	@Autowired
	private productRepository productRepo;
	@Autowired
	private userRepository userRepo;
	
	@GetMapping("/")
	public String products(Model model,HttpServletRequest request) {
		Main.getSessionAttribute(model, request);
		model.addAttribute("products", productRepo.findAll());
		return "home";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "admin");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("normalProduct", new normalProduct());
			model.addAttribute("onlineProduct", new onlineProduct());
			model.addAttribute("brands", brandRepo.findAll());
			return "addProduct";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/addNormalProduct")
	public String addProduct(@ModelAttribute normalProduct product) {
		productRepo.save(product);
		return "redirect:/addProduct";
	}
	
	@PostMapping("/addOnlineProduct")
	public String addProduct(@ModelAttribute onlineProduct product) {
		productRepo.save(product);
		return "redirect:/addProduct";
	}
	

}
