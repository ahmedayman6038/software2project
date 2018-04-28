package software2project.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import software2project.Main;
import software2project.models.statistic;
import software2project.models.user;
import software2project.repository.statisticRepository;
import software2project.repository.userRepository;

/**
 * This is a statistics Controller Responsible for Handling Requests and Operations Related to Statistic Model
 * @author Rick & morty
 *
 */
@Controller
public class statisticsController {
	@Autowired
	private statisticRepository statisticRepo;
	@Autowired
	private userRepository userRepo;
	
	@GetMapping("/addStatistic")
	public String addStatistic(Model model,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		user user = userRepo.checkType(email, "admin");
		if(user != null) {
			Main.getSessionAttribute(model, request);
			model.addAttribute("statistics", statisticRepo.findAll());
			return "addStatistic";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/addStatistic")
	public String addStatistic(@RequestParam("statisticId") Integer statisticId) {
		statistic statistic = statisticRepo.findOne(statisticId);
		statistic.setAdded(true);
		statisticRepo.save(statistic);
		return "redirect:/addStatistic";
	}
}
