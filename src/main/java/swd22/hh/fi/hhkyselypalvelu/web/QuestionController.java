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
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;

@Controller // Controller for questions made with thymeleaf
public class QuestionController {
	
	@Autowired
	private OpenTextQuestionRepository questionrepo;
	@Autowired
	private QueryRepository queryrepo;
	
	
	//RECEIVES EMPTY QUESTION 
	@GetMapping("/createquestions/{id}")
	public String getQuestionCreation(@PathVariable("id") Long id,Model model) {
		OpenTextQuestion question1 = new OpenTextQuestion();
		Query query = queryrepo.findById(id).get();
		question1.setQuery(query);
		model.addAttribute("question", question1);
		model.addAttribute("questions", query.getQuestions());
		return "createquestion";
	}
	
	// save question
	@PostMapping("/savequestion")
	public String saveQuestion(@ModelAttribute OpenTextQuestion question, Model model) {
		
		questionrepo.save(question);		
		return "redirect:/createquestions/"+question.getQuery().getQueryId(); // redirect to add new questions
	}

}
