package swd22.hh.fi.hhkyselypalvelu.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.Question;
import swd22.hh.fi.hhkyselypalvelu.domain.QuestionRepository;

@Controller
public class IndexController {
	@Autowired
	private QuestionRepository questionrepo;
	@Autowired
	private QueryRepository queryrepo;
	
	// FRONT PAGE
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/questionamount")
	public String getQuestionAmount(Model model) {
		return "questionamount";
	}

	// FORM TO CREATE NEW QUERIES(FORMS) REQUIRES AMOUNT OF QUESTIONS
	@GetMapping("/newquery")
	public String getCreateform(Model model) {
		model.addAttribute("query", new Query());
		model.addAttribute("queries", queryrepo.findAll());
		return "newquery";
	}
	
	
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
		String create = "createquestions/"+query.getQueryId();
		return "redirect:/"+create;
	}
	
	//RECEIVES EMPTY QUESTION 
	@GetMapping("/createquestions/{id}")
	public String getQuestionCreation(@PathVariable("id") Long id,Model model) {
		Question question1 = new Question();
		question1.setQuery(queryrepo.findById(id).get());
		model.addAttribute("question", question1);
		return "createquestion";
	}
	
	// save question
	@PostMapping("/savequestion")
	public String saveQuestion(@ModelAttribute Question question, Model model) {
		
		questionrepo.save(question);
		Question question1 = new Question();
		question1.setQuery(question.getQuery());
		model.addAttribute("question",question1);
		
		return "createquestion"; // redirect to add new questions
	}
	
	
}
