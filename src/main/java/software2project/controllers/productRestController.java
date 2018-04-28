package software2project.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import software2project.services.productService;

@RestController
public class productRestController {
	@Autowired
	private productService ps;
	@GetMapping("/calculate/{price}/{quantity}")
	public float calculateTotalPrice(HttpServletRequest request,@PathVariable Float price,@PathVariable Integer quantity) {
		String type = (String) request.getSession().getAttribute("type");
		Integer id = (Integer) request.getSession().getAttribute("id");
		return ps.calculateTotalPrice(price,quantity,type,id);
	}
}
