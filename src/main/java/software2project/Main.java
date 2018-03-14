package software2project;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

/**
 * This is The Main Class Responsible For Start The Application and Any Global Operations Used in The Application  
 * @author Rick & morty
 *
 */
@SpringBootApplication
public class Main {
	public static void getSessionAttribute(Model model,HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("id");
		String email = (String) request.getSession().getAttribute("email");
		String type = (String) request.getSession().getAttribute("type");
		String name = (String) request.getSession().getAttribute("name");
		model.addAttribute("id",id);
		model.addAttribute("email",email);
		model.addAttribute("type",type);
		model.addAttribute("name",name);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        SpringApplication.run(Main.class, args);
	}

}
