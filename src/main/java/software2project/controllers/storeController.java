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
import software2project.services.productService;
import software2project.services.historyService;

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
	@Autowired
	private productService productService;
	@Autowired
	private historyService historyService;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "storeOwner");
		user user2 = userRepo.checkType(email, "collaborator");
		if(user != null || user2 != null) {
			Main.getSessionAttribute(model, request);
			if(user != null) {
				model.addAttribute("stores1", storeRepo.getNormalStores(user.getId()));
				model.addAttribute("stores2", storeRepo.getOnlineStores(user.getId()));
			}else {
				model.addAttribute("stores1", storeRepo.getNormalStores(user2.getCollaborated()));
				model.addAttribute("stores2", storeRepo.getOnlineStores(user2.getCollaborated()));
			}
			return "dashboard";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/showStores")
	public String showStores(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "admin");
		if(user != null) {
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
		user user = userRepo.checkType(email, "storeOwner");
		if(user != null) {
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
		user users = userRepo.checkType(email, "storeOwner");
		if(users != null) {
			Main.getSessionAttribute(model, request);
			user user = new user();
			user = users;
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
	
	@GetMapping("/dashboard/show/{type}/{id}")
	public String showProductOfStore(@PathVariable String type,@PathVariable Integer id,Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "storeOwner");
		user user2 = userRepo.checkType(email, "collaborator");
		if(user != null || user2 != null) {
			Main.getSessionAttribute(model, request);
			if(type.equals("normal")) {
				model.addAttribute("products", productRepo.getStoreNormalProducts(id));
			}else {
				model.addAttribute("products", productRepo.getStoreOnlineProducts(id));
			}
			model.addAttribute("storeId", id);
			model.addAttribute("type", type);
			return "showProductOfStore";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/dashboard/delete/{type}/{pid}/{sid}")
	public String deleteProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		storeProducts sproduct = new storeProducts();
		sproduct = productRepo.getStoreProduct(pid, sid);
		storeRepo.deleteStoreProducts(pid, sid);
		String email = (String) request.getSession().getAttribute("email");
		historyService.addHistory(sproduct,email,"deleteProduct");
		return "redirect:/dashboard/show/"+type+"/"+sid;
	}
	
	@GetMapping("/dashboard/offer/{type}/{pid}/{sid}")
	public String offerProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "storeOwner");
		user user2 = userRepo.checkType(email, "collaborator");
		if(user != null || user2 != null) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("storeProduct", productRepo.getStoreProduct(pid, sid));
			model.addAttribute("type", type);
			return "addOffer";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/dashboard/offer/{type}/{pid}/{sid}")
	public String offerProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid,@RequestParam("offer") Integer offer) {
		storeProducts sproduct = new storeProducts();
		sproduct = productRepo.getStoreProduct(pid, sid);
		storeRepo.addOffer(offer, pid, sid);
		String email = (String) request.getSession().getAttribute("email");
		historyService.addHistory(sproduct,email,"addOffer");
		return "redirect:/dashboard/show/"+type+"/"+sid;
	}
	
	@GetMapping("/dashboard/dOffer/{type}/{pid}/{sid}")
	public String dOfferProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		storeProducts sproduct = new storeProducts();
		sproduct = productRepo.getStoreProduct(pid, sid);
		storeRepo.deleteOffer(pid, sid);
		String email = (String) request.getSession().getAttribute("email");
		historyService.addHistory(sproduct,email,"deleteOffer");
		return "redirect:/dashboard/show/"+type+"/"+sid;
	}
	
	@GetMapping("/dashboard/edit/{type}/{pid}/{sid}")
	public String editProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "storeOwner");
		user user2 = userRepo.checkType(email, "collaborator");
		if(user != null || user2 != null) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("storeProduct", productRepo.getStoreProduct(pid, sid));
			model.addAttribute("brands", brandRepo.findAll());
			model.addAttribute("type", type);
			return "editProductOfStore";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/dashboard/edit/{type}/{pid}/{sid}")
	public String editProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@ModelAttribute storeProducts sproducts,@PathVariable Integer pid,@PathVariable Integer sid) {
		if(type.equals("normal")) {
			normalProduct nproduct = productRepo.getNormalProduct(pid);
			if(sproducts.getPrice() < nproduct.getStartPrice() || sproducts.getPrice() > nproduct.getEndPrice()) {
				model.addAttribute("error", "The Price Must Be Between " + nproduct.getStartPrice() +" and "+ nproduct.getEndPrice());
				model.addAttribute("storeProduct", productRepo.getStoreProduct(pid, sid));
				model.addAttribute("brands", brandRepo.findAll());
				model.addAttribute("type", type);
				return "editProductOfStore";
			}
			storeProducts sproduct = new storeProducts();
			sproduct = productRepo.getStoreProduct(pid, sid);
			storeRepo.updateStoreProducts(sproducts.getPrice(), sproducts.getQuantity(), sproducts.getBrand().getId(),pid, sid);
			String email = (String) request.getSession().getAttribute("email");
			historyService.addHistory(sproduct,email,"editProduct");
			return "redirect:/dashboard/show/"+type+"/"+sid;
		}else {
			onlineProduct oproduct = productRepo.getOnlineProduct(pid);
			if(sproducts.getPrice() < oproduct.getStartPrice() || sproducts.getPrice() > oproduct.getEndPrice()) {
				model.addAttribute("error", "The Price Must Be Between " + oproduct.getStartPrice() +" and "+ oproduct.getEndPrice());
				model.addAttribute("storeProduct", productRepo.getStoreProduct(pid, sid));
				model.addAttribute("brands", brandRepo.findAll());
				model.addAttribute("type", type);
				return "editProductOfStore";
			}
			storeProducts sproduct = new storeProducts();
			sproduct = productRepo.getStoreProduct(pid, sid);
			storeRepo.updateStoreProducts(sproducts.getPrice(), sproducts.getQuantity(), sproducts.getBrand().getId(),pid, sid);
			String email = (String) request.getSession().getAttribute("email");
			historyService.addHistory(sproduct,email,"editProduct");
			return "redirect:/dashboard/show/"+type+"/"+sid;
		}
	}
	
	@GetMapping("/dashboard/add/{type}/{id}")
	public String addProductToStore(@PathVariable String type,@PathVariable Integer id,Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "storeOwner");
		user user2 = userRepo.checkType(email, "collaborator");
		if(user != null || user2 != null) {
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
	public String addProductToStore(HttpServletRequest request,@PathVariable String type,@RequestParam("storeId") Integer storeId,@RequestParam("productId") Integer productId,@RequestParam("brand") brand brand,@RequestParam("quantity") Integer quantity,@RequestParam("price") Float price,Model model) {
		if(type.equals("normal")) {
			normalStore nstore = storeRepo.getNormalStore(storeId);
			normalProduct nproduct = productRepo.getNormalProduct(productId);
			if(!productService.addProductToStore(nstore,nproduct,price,brand,quantity)) {
				model.addAttribute("error", "The Price Must Be Between " + nproduct.getStartPrice() +" and "+ nproduct.getEndPrice());
				model.addAttribute("products", productRepo.getNormalProducts());
				model.addAttribute("brands", brandRepo.findAll());
				model.addAttribute("storeId", storeId);
				model.addAttribute("type", type);
				return "addProductToStore";
			}
			String email = (String) request.getSession().getAttribute("email");
			historyService.addHistory(nproduct,nstore,email,"addProduct",brand,price,quantity);
			return "redirect:/dashboard/show/"+type+"/"+storeId.toString();
		}else {
			onlineStore ostore = storeRepo.getOnlineStore(storeId);
			onlineProduct oproduct = productRepo.getOnlineProduct(productId);
			if(!productService.addProductToStore(ostore,oproduct,price,brand,quantity)) {
				model.addAttribute("error", "The Price Must Be Between " + oproduct.getStartPrice() +" and "+ oproduct.getEndPrice());
				model.addAttribute("products", productRepo.getOnlineProducts());
				model.addAttribute("brands", brandRepo.findAll());
				model.addAttribute("storeId", storeId);
				model.addAttribute("type", type);
				return "addProductToStore";
			}
			String email = (String) request.getSession().getAttribute("email");
			historyService.addHistory(oproduct,ostore,email,"addProduct",brand,price,quantity);
			return "redirect:/dashboard/show/"+type+"/"+storeId.toString();
		}
	}
	
	
	@GetMapping("/dashboard/addCollaborator")
	public String addCollaborator(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "storeOwner");
		if(user != null) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("user", new user());
			model.addAttribute("oid", user.getId());
			return "addCollaborator";
		}
		return "redirect:/login";

	}
	
	@PostMapping("/dashboard/addCollaborator/{oid}")
	public String addCollaborator(@ModelAttribute user user,Model model,@PathVariable Integer oid, HttpServletRequest request) {
		user users = userRepo.findByEmail(user.getEmail());
		if(users != null) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("error", "This email alerady exist");
			return "addCollaborator";
		} else {
			user.setCollaborated(oid);
			user.setType("collaborator");
			userRepo.save(user);
			return "redirect:/dashboard/addCollaborator";
		}
	}
	
}
