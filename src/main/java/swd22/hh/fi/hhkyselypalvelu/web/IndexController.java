package swd22.hh.fi.hhkyselypalvelu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	//FRONT PAGE
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/questionamount")
	public String getQuestionAmount(Model model) {
		return "questionamount";
	}
	
	
	//FORM TO CREATE NEW QUERIES(FORMS) REQUIRES AMOUNT OF QUESTIONS
	@GetMapping("/createform")
	public String getCreateform() {
		return "createform";
	}

}
