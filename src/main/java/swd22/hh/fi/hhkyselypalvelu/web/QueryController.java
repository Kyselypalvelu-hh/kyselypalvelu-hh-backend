package swd22.hh.fi.hhkyselypalvelu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	// LIST ALL QUERIES
	@GetMapping("/allqueries")
	public String listQueries(Model model) {
		 model.addAttribute("queries", queryrepo.findAll());
		
		return "allqueries";
	}
	
	// EDIT QUERY
	@GetMapping("editquery/{id}")
	public String editQuery(@PathVariable(name = "id") Long id, Model model) {
		Query query = queryrepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid query Id:" + id));
		model.addAttribute(query);
		return "editquery";
	}
	// SAVE UPDATED QUERY
	@PostMapping("update/{id}")
	public String updateQuery(@PathVariable(name = "id") Long id, Query query) {
		queryrepo.save(query);
		return "redirect:/allqueries";
	}
	
	// DELETE QUERY
	@GetMapping("/deletequery/{id}")
	public String deleteQuery(@PathVariable(name = "id") Long id) {
		queryrepo.deleteById(id);
		return "redirect:/allqueries";
	}
}
