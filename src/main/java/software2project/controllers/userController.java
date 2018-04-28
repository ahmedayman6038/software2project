package software2project.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import software2project.Main;
import software2project.models.user;
import software2project.services.userService;

/**
 * This is a User Controller Responsible for Handling Requests and Operations Related to User Model  
 * @author Rick & morty
 *
 */
@Controller
public class userController  {
	
	@Autowired
	private userService userService;
	
	@GetMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		Main.getSessionAttribute(model, request);
		model.addAttribute("user", new user());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute user user,Model model,HttpServletRequest request) {
		user users = userService.login(user);
		if(users != null) {
			request.getSession().setAttribute("id", users.getId());
			request.getSession().setAttribute("email", users.getEmail());
			request.getSession().setAttribute("type", users.getType());
			request.getSession().setAttribute("name", users.getName());
			if(users.getType().equals("storeOwner")) {
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
		if(userService.register(user) == false) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("error", "This email alerady exist");
			return "register";
		} else {
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
