package software2project.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import software2project.Main;
import software2project.models.*;
import software2project.repository.*;

@Controller
public class historyController {
	@Autowired
	private historyRepository historyRepo;
	@Autowired
	private userRepository userRepo;
	@Autowired
	private productRepository productRepo;
	@Autowired
	private storeRepository storeRepo;
	
	@GetMapping("/dashboard/history/{sid}")
	public String showHistory(Model model,HttpServletRequest request,@PathVariable Integer sid) {
		String email = (String) request.getSession().getAttribute("email");
		List<user> users = userRepo.checkType(email, "storeOwner");
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("historys", historyRepo.getStoreHistory(sid));
			return "showHistory";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/undo/{hid}/{type}/{pid}/{sid}")
	public String undoHistory(@PathVariable Integer hid,@PathVariable String type,@PathVariable Integer pid,@PathVariable Integer sid) {
		if(type.equals("addProduct")) {
			storeRepo.deleteStoreProducts(pid, sid);
		}else if(type.equals("editProduct")) {
			history hist = new history();
			hist = historyRepo.getHistory(hid);
			storeRepo.updateStoreProducts(hist.getPrice(), hist.getQuantity(), hist.getBrandId(),pid, sid);
		}else if(type.equals("deleteProduct")) {
			history hist = new history();
			hist = historyRepo.getHistory(hid);
			store store = storeRepo.findById(sid);
			product product = productRepo.findById(pid);
			storeProducts storeProduct = new storeProducts();
			storeProduct.setStore(store);
			storeProduct.setProduct(product);
			brand br = new brand();
			br.setId(hist.getBrandId());
			storeProduct.setBrand(br);
			storeProduct.setPrice(hist.getPrice());
			storeProduct.setOffer(hist.getOffer());
			storeProduct.setQuantity(hist.getQuantity());
			storeProduct.setUserViewed(hist.getUserViewed());
			storeProduct.setLastBuyedDate(hist.getLastBuyedDate());
			store.getProducts().add(storeProduct);
			productRepo.save(product);
			storeRepo.save(store);
		}else {
			history hist = new history();
			hist = historyRepo.getHistory(hid);
			storeRepo.addOffer(hist.getOffer(), pid, sid);
		}
		historyRepo.deleteHistory(hid);
		return "redirect:/dashboard/history/"+sid;
	}
}
