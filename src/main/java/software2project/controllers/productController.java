package software2project.controllers;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private productRepository productRepo;
	@Autowired
	private userRepository userRepo;
	@Autowired
	private storeRepository storeRepo;
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
	
	@GetMapping("/view/{pid}/{sid}")
	public String buyProduct(Model model,HttpServletRequest request,@PathVariable Integer pid,@PathVariable Integer sid) {
		Integer id = (Integer) request.getSession().getAttribute("id");
		if(id != null) {
			Main.getSessionAttribute(model, request);
			storeProducts sproduct =  productRepo.getStoreProduct(pid, sid);
			Integer old = sproduct.getUserViewed();
			sproduct.setUserViewed(old+1);
			productRepo.save(productRepo.findById(pid));
			Float price = sproduct.getPrice();
			if(sproduct.getOffer() != null) {
				float offer = (float) sproduct.getOffer();
				Float per = (float) (offer/100.0);
				Float disc = price*per;
				sproduct.setPrice(price-disc);
			}
			model.addAttribute("storeProduct", sproduct);
			return "buyProduct";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/buy/{pid}/{sid}")
	public String buyProduct(Model model,HttpServletRequest request,@PathVariable Integer pid,@PathVariable Integer sid,@RequestParam("quantity") Integer quantity,@RequestParam("address") String address) {
		Integer id = (Integer) request.getSession().getAttribute("id");
		storeProducts product = productRepo.getStoreProduct(pid, sid);
		Integer old = product.getQuantity();
		if(quantity > old) {
			model.addAttribute("error", "This Quantity Not Availble");
			model.addAttribute("storeProduct", productRepo.getStoreProduct(pid, sid));
			return "buyProduct";
		}
		product.setQuantity(old-quantity);
		product.setLastBuyedDate(new Date());
		product p = productRepo.findById(pid);
		store s = storeRepo.findById(sid);
		user u = userRepo.findById(id);
		buyProducts buy = new buyProducts();
		buy.setProduct(p);
		buy.setStore(s);
		buy.setUser(u);
		buy.setAddress(address);
		u.getBuyed().add(buy);
		productRepo.save(p);
		return "redirect:/";
	}
	

}
