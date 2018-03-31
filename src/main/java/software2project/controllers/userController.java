package software2project.controllers;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import software2project.Main;
import software2project.models.user;
import software2project.repository.userRepository;

/**
 * This is a User Controller Responsible for Handling Requests and Operations Related to User Model  
 * @author Rick & morty
 *
 */
@Controller
public class userController  {
	
	@Autowired
	private userRepository userRepo;
	
	@GetMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		Main.getSessionAttribute(model, request);
		model.addAttribute("user", new user());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute user user,Model model,HttpServletRequest request) {
		List<user> users = userRepo.findUser(user.getEmail(), user.getPassword());
		if(users.size() > 0) {
			request.getSession().setAttribute("id", users.get(0).getId());
			request.getSession().setAttribute("email", users.get(0).getEmail());
			request.getSession().setAttribute("type", users.get(0).getType());
			request.getSession().setAttribute("name", users.get(0).getName());
			if(users.get(0).getType().equals("storeOwner")) {
				return "redirect:/dashboard";
			}else {
				return "redirect:/";
			}
		} else {
			Main.getSessionAttribute(model, request);
			model.addAttribute("error", "This email or password is incorrect");
			return "login";
		}
	}
	
	
	
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		Main.getSessionAttribute(model, request);
		model.addAttribute("user", new user());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute user user,Model model, HttpServletRequest request) {
		List<user> users = userRepo.findByEmail(user.getEmail());
		if(users.size() > 0) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("error", "This email alerady exist");
			return "register";
		} else {
			userRepo.save(user);
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
		return "redirect:/";
	}
}
