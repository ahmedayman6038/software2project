package software2project.controllers;

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
 * This is a Store Controller Responsible for Handling Requests and Operations Related to Store Model 
 * @author Rick & morty
 *
 */
@Controller
public class storeController {

	@Autowired
	private storeRepository storeRepo;
	@Autowired
	private userRepository userRepo;
	@Autowired
	private productRepository productRepo;
	@Autowired
	private statisticRepository statisticRepo;
	@Autowired
	private brandRepository brandRepo;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("stores1", storeRepo.getNormalStores(users.get(0).getId()));
			model.addAttribute("stores2", storeRepo.getOnlineStores(users.get(0).getId()));
			return "dashboard";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/showStores")
	public String showStores(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "admin");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("stores1", storeRepo.getAllNormalStores());
			model.addAttribute("stores2", storeRepo.getAllOnlineStores());
			return "stores";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/dashboard/{name}/{id}")
	public String showStore(@PathVariable String name,@PathVariable Integer id, Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("storeName", name);
			model.addAttribute("storeId", id);
			List<statistic> statistic = (List<statistic>) statisticRepo.findAll();
			for (int i = 0; i < statistic.size(); i++) {
				model.addAttribute("statisticName"+statistic.get(i).getId().toString(), statistic.get(i).getName());
				model.addAttribute("statisticAdded"+statistic.get(i).getId().toString(), statistic.get(i).isAdded());
			}
			return "store";
		}
		return "redirect:/login";
	}
	

	
	@GetMapping("/dashboard/addStore")
	public String addStore(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			user user = new user();
			user = users.get(0);
			Main.getSessionAttribute(model, request);
			normalStore store1 = new normalStore();
			store1.setUser(user);
			onlineStore store2 = new onlineStore();
			store2.setUser(user);
			model.addAttribute("normalStore", store1);
			model.addAttribute("onlineStore", store2);
			return "addStore";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/addNormalStore")
	public String addStore(@ModelAttribute normalStore store) {
		storeRepo.save(store);
		return "redirect:/dashboard/addStore";
	}
	
	@PostMapping("/addOnlineStore")
	public String addStore(@ModelAttribute onlineStore store) {
		storeRepo.save(store);
		return "redirect:/dashboard/addStore";
	}
	
	@GetMapping("/dashboard/add/{type}/{id}")
	public String addProductToStore(@PathVariable String type,@PathVariable Integer id,Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			if(type.equals("normal")) {
				model.addAttribute("products", productRepo.getNormalProducts());
			}else {
				model.addAttribute("products", productRepo.getOnlineProducts());
			}
			model.addAttribute("brands", brandRepo.findAll());
			model.addAttribute("storeId", id);
			model.addAttribute("type", type);
			return "addProductToStore";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/addProductToStore/{type}")
	public String addProductToStore(@PathVariable String type,@RequestParam("storeId") Integer storeId,@RequestParam("productId") Integer productId,@RequestParam("brand") brand brand,@RequestParam("quantity") Integer quantity,@RequestParam("price") Float price,Model model) {
		if(type.equals("normal")) {
			normalStore nstore = storeRepo.getNormalStore(storeId);
			normalProduct nproduct = productRepo.getNormalProduct(productId);
			if(price < nproduct.getStartPrice() || price > nproduct.getEndPrice()) {
				model.addAttribute("error", "The Price Must Be Between " + nproduct.getStartPrice() +" and "+ nproduct.getEndPrice());
				model.addAttribute("products", productRepo.getNormalProducts());
				model.addAttribute("storeId", storeId);
				model.addAttribute("type", type);
				return "addProductToStore";
			}
			storeProducts storeProduct = new storeProducts();
			storeProduct.setStore(nstore);
			storeProduct.setProduct(nproduct);
			storeProduct.setBrand(brand);
			storeProduct.setPrice(price);
			storeProduct.setQuantity(quantity);
			storeProduct.setUserBuyed(0);
			storeProduct.setUserViewed(0);
			nstore.getProducts().add(storeProduct);
			productRepo.save(nproduct);
			storeRepo.save(nstore);	
			return "redirect:/dashboard/add/"+type+"/"+storeId.toString();
		}else {
			onlineStore ostore = storeRepo.getOnlineStore(storeId);
			onlineProduct oproduct = productRepo.getOnlineProduct(productId);
			if(price < oproduct.getStartPrice() || price > oproduct.getEndPrice()) {
				model.addAttribute("error", "The Price Must Be Between ");
				model.addAttribute("products", productRepo.getOnlineProducts());
				model.addAttribute("storeId", storeId);
				model.addAttribute("type", type);
				return "addProductToStore";
			}
			storeProducts storeProduct = new storeProducts();
			storeProduct.setStore(ostore);
			storeProduct.setProduct(oproduct);
			storeProduct.setBrand(brand);
			storeProduct.setPrice(price);
			storeProduct.setQuantity(quantity);
			storeProduct.setUserBuyed(0);
			storeProduct.setUserViewed(0);
			ostore.getProducts().add(storeProduct);
			productRepo.save(oproduct);
			storeRepo.save(ostore);
			return "redirect:/dashboard/add/"+type+"/"+storeId.toString();
		}
	}
	
	
}
