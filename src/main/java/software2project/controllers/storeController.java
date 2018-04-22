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
	private historyRepository historyRepo;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		List<user> users2 = userRepo.checkType(email, "collaborator");
		if(users.size() > 0 || users2.size() > 0) {
			Main.getSessionAttribute(model, request);
			if(users.size() > 0) {
				model.addAttribute("stores1", storeRepo.getNormalStores(users.get(0).getId()));
				model.addAttribute("stores2", storeRepo.getOnlineStores(users.get(0).getId()));
			}else {
				model.addAttribute("stores1", storeRepo.getNormalStores(users2.get(0).getCollaborated()));
				model.addAttribute("stores2", storeRepo.getOnlineStores(users2.get(0).getCollaborated()));
			}
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
	
	@GetMapping("/dashboard/show/{type}/{id}")
	public String showProductOfStore(@PathVariable String type,@PathVariable Integer id,Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		List<user> users2 = userRepo.checkType(email, "collaborator");
		if(users.size() > 0 || users2.size() > 0) {
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
		List<user> users = userRepo.findByEmail(email);
		history history = new history();
		history.setDate(new Date());
		history.setBrandId(sproduct.getBrand().getId());
		history.setProductName(sproduct.getProduct().getName());
		history.setPrice(sproduct.getPrice());
		history.setProductId(sproduct.getProduct().getId());
		history.setStoreId(sproduct.getStore().getId());
		history.setQuantity(sproduct.getQuantity());
		history.setOffer(sproduct.getOffer());
		history.setUserViewed(sproduct.getUserViewed());
		history.setLastBuyedDate(sproduct.getLastBuyedDate());
		history.setUser(users.get(0));
		history.setType("deleteProduct");
		historyRepo.save(history);
		return "redirect:/dashboard/show/"+type+"/"+sid;
	}
	
	@GetMapping("/dashboard/offer/{type}/{pid}/{sid}")
	public String offerProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		List<user> users2 = userRepo.checkType(email, "collaborator");
		if(users.size() > 0 || users2.size() > 0) {
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
		List<user> users = userRepo.findByEmail(email);
		history history = new history();
		history.setDate(new Date());
		history.setBrandId(sproduct.getBrand().getId());
		history.setProductName(sproduct.getProduct().getName());
		history.setPrice(sproduct.getPrice());
		history.setProductId(sproduct.getProduct().getId());
		history.setStoreId(sproduct.getStore().getId());
		history.setQuantity(sproduct.getQuantity());
		history.setOffer(sproduct.getOffer());
		history.setUserViewed(sproduct.getUserViewed());
		history.setLastBuyedDate(sproduct.getLastBuyedDate());
		history.setUser(users.get(0));
		history.setType("addOffer");
		historyRepo.save(history);
		return "redirect:/dashboard/show/"+type+"/"+sid;
	}
	
	@GetMapping("/dashboard/dOffer/{type}/{pid}/{sid}")
	public String dOfferProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		storeProducts sproduct = new storeProducts();
		sproduct = productRepo.getStoreProduct(pid, sid);
		storeRepo.deleteOffer(pid, sid);
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.findByEmail(email);
		history history = new history();
		history.setDate(new Date());
		history.setBrandId(sproduct.getBrand().getId());
		history.setProductName(sproduct.getProduct().getName());
		history.setPrice(sproduct.getPrice());
		history.setProductId(sproduct.getProduct().getId());
		history.setStoreId(sproduct.getStore().getId());
		history.setQuantity(sproduct.getQuantity());
		history.setOffer(sproduct.getOffer());
		history.setUserViewed(sproduct.getUserViewed());
		history.setLastBuyedDate(sproduct.getLastBuyedDate());
		history.setUser(users.get(0));
		history.setType("deleteOffer");
		historyRepo.save(history);
		return "redirect:/dashboard/show/"+type+"/"+sid;
	}
	
	@GetMapping("/dashboard/edit/{type}/{pid}/{sid}")
	public String editProductOfStore(Model model,HttpServletRequest request,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		List<user> users2 = userRepo.checkType(email, "collaborator");
		if(users.size() > 0 || users2.size() > 0) {
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
			List<user> users = userRepo.findByEmail(email);
			history history = new history();
			history.setDate(new Date());
			history.setBrandId(sproduct.getBrand().getId());
			history.setProductName(sproduct.getProduct().getName());
			history.setPrice(sproduct.getPrice());
			history.setProductId(sproduct.getProduct().getId());
			history.setStoreId(sproduct.getStore().getId());
			history.setQuantity(sproduct.getQuantity());
			history.setOffer(sproduct.getOffer());
			history.setUserViewed(sproduct.getUserViewed());
			history.setLastBuyedDate(sproduct.getLastBuyedDate());
			history.setUser(users.get(0));
			history.setType("editProduct");
			historyRepo.save(history);
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
			List<user> users = userRepo.findByEmail(email);
			history history = new history();
			history.setDate(new Date());
			history.setBrandId(sproduct.getBrand().getId());
			history.setProductName(sproduct.getProduct().getName());
			history.setPrice(sproduct.getPrice());
			history.setProductId(sproduct.getProduct().getId());
			history.setStoreId(sproduct.getStore().getId());
			history.setQuantity(sproduct.getQuantity());
			history.setUserViewed(sproduct.getUserViewed());
			history.setLastBuyedDate(sproduct.getLastBuyedDate());
			history.setUser(users.get(0));
			history.setType("editProduct");
			historyRepo.save(history);
			return "redirect:/dashboard/show/"+type+"/"+sid;
		}
	}
	
	@GetMapping("/dashboard/add/{type}/{id}")
	public String addProductToStore(@PathVariable String type,@PathVariable Integer id,Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		List<user> users2 = userRepo.checkType(email, "collaborator");
		if(users.size() > 0 || users2.size() > 0) {
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
			if(price < nproduct.getStartPrice() || price > nproduct.getEndPrice()) {
				model.addAttribute("error", "The Price Must Be Between " + nproduct.getStartPrice() +" and "+ nproduct.getEndPrice());
				model.addAttribute("products", productRepo.getNormalProducts());
				model.addAttribute("brands", brandRepo.findAll());
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
			storeProduct.setUserViewed(0);
			nstore.getProducts().add(storeProduct);
			productRepo.save(nproduct);
			storeRepo.save(nstore);
			String email = (String) request.getSession().getAttribute("email");
			List<user> users = userRepo.findByEmail(email);
			history history = new history();
			history.setDate(new Date());
			history.setBrandId(brand.getId());
			history.setProductName(nproduct.getName());
			history.setPrice(price);
			history.setProductId(productId);
			history.setStoreId(storeId);
			history.setQuantity(quantity);
			history.setUser(users.get(0));
			history.setUserViewed(0);
			history.setType("addProduct");
			historyRepo.save(history);
			return "redirect:/dashboard/show/"+type+"/"+storeId.toString();
		}else {
			onlineStore ostore = storeRepo.getOnlineStore(storeId);
			onlineProduct oproduct = productRepo.getOnlineProduct(productId);
			if(price < oproduct.getStartPrice() || price > oproduct.getEndPrice()) {
				model.addAttribute("error", "The Price Must Be Between " + oproduct.getStartPrice() +" and "+ oproduct.getEndPrice());
				model.addAttribute("products", productRepo.getOnlineProducts());
				model.addAttribute("brands", brandRepo.findAll());
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
			storeProduct.setUserViewed(0);
			ostore.getProducts().add(storeProduct);
			productRepo.save(oproduct);
			storeRepo.save(ostore);
			String email = (String) request.getSession().getAttribute("email");
			List<user> users = userRepo.findByEmail(email);
			history history = new history();
			history.setDate(new Date());
			history.setBrandId(brand.getId());
			history.setProductName(oproduct.getName());
			history.setPrice(price);
			history.setProductId(productId);
			history.setStoreId(storeId);
			history.setQuantity(quantity);
			history.setUser(users.get(0));
			history.setUserViewed(0);
			history.setType("addProduct");
			historyRepo.save(history);
			return "redirect:/dashboard/show/"+type+"/"+storeId.toString();
		}
	}
	
	
	@GetMapping("/dashboard/addCollaborator")
	public String addCollaborator(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("user", new user());
			model.addAttribute("oid", users.get(0).getId());
			return "addCollaborator";
		}
		return "redirect:/login";

	}
	
	@PostMapping("/dashboard/addCollaborator/{oid}")
	public String addCollaborator(@ModelAttribute user user,Model model,@PathVariable Integer oid, HttpServletRequest request) {
		List<user> users = userRepo.findByEmail(user.getEmail());
		if(users.size() > 0) {
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
