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
import software2project.models.*;
import software2project.repository.*;

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
	
	@GetMapping("/addProduct")
	public String addProduct(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "admin");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("product", new product());
			model.addAttribute("brands", brandRepo.findAll());
			return "addProduct";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute product product) {
		productRepo.save(product);
		return "redirect:/addProduct";
	}
	

}
