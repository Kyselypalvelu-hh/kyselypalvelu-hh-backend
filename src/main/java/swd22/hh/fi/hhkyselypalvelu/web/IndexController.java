package swd22.hh.fi.hhkyselypalvelu.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller for front page
@Controller
public class IndexController {
	

	
	// FRONT PAGE
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	

	

	
	
}
