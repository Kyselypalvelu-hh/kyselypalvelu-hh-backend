package swd22.hh.fi.hhkyselypalvelu.web;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;

@Controller // Controller for questions made with thymeleaf
public class QuestionController {
	//jouni
	//
	@Autowired
	private OpenTextQuestionRepository questionrepo;
	@Autowired
	private QueryRepository queryrepo;
	@Autowired
	private MultipleChoiceQuestionRepository multichoicequestionrepo;
	
	//RECEIVES EMPTY QUESTION 
	@GetMapping("/createquestions/{id}")
	public String getQuestionCreation(@PathVariable("id") Long id,Model model) {
		OpenTextQuestion textquestion = new OpenTextQuestion();
		MultipleChoiceQuestion multichoicequestion = new MultipleChoiceQuestion();
		Query query = queryrepo.findById(id).get();
		textquestion.setQuery(query);
		multichoicequestion.setQuery(query);
		model.addAttribute("question", textquestion);
		model.addAttribute("questions", query.getTextQuestions());
		model.addAttribute("multichoisequestion", multichoicequestion);
		return "createquestion";
	}
	
	// save question
	@PostMapping("/savequestion")
	public String saveQuestion(@ModelAttribute OpenTextQuestion question, Model model) {
		
		questionrepo.save(question);		
		return "redirect:/createquestions/"+question.getQuery().getId(); // redirect to add new questions
	}
	
	@PostMapping("/savemultichoicequestion")
	public String saveMultiChoiceQuestion(@ModelAttribute MultipleChoiceQuestion multiquestion, Model model) {
		
		multichoicequestionrepo.save(multiquestion);		
		return "redirect:/createmultichoiceanswers/"+multiquestion.getQuestionId(); // redirect to add new questions
	}
	/**
	@GetMapping("/createmultichoiceanswers/{id}")
	public String getMultipleChoiceAnswerCreation(@PathVariable("id") Long id,Model model) {
		MultipleChoiceAnswer multianswers = new MultipleChoiceAnswer();
		MultipleChoiceQuestion multiquestion = multichoicequestionrepo.findById(id).get();
		model.addAttribute("answers", multianswers);
		return "addmultianswers";
	}**/
}
