package swd22.hh.fi.hhkyselypalvelu.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	// save question
	@RequestMapping(value = "/savequestion", method = RequestMethod.POST)
	public String saveQuestion(Question question) {

		
		return "redirect:/newquery"; // redirect to add new questions
	}
}
