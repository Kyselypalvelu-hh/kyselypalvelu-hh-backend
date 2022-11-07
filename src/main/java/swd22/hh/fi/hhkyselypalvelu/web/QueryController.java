package swd22.hh.fi.hhkyselypalvelu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;

@Controller // Controller for queries made with thymeleaf
public class QueryController {
	
	@Autowired
	private QueryRepository queryrepo;
	
	//CREATES NEW QUERY AND ASKS FOR THE TITLE FOR THE ENTIRE FORM(QUERY)
	@GetMapping("/createquery")
	public String getTest(Model model) {
		model.addAttribute("query", new Query());
		return "createquery";
		
	}
	//SAVES QUERY AND REDIRECTS TO PAGE WHERE YOU ADD QUESTIONS
	@PostMapping("/savequery")
	public String postTest(@ModelAttribute Query query, Model model) {
		queryrepo.save(query);
		
		//sends forward id of the query so questions can be connected to query
		String create = "createquestions/"+query.getId();
		return "redirect:/"+create;
	}
	
	
}
