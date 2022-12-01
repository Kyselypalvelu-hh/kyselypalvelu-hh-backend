package swd22.hh.fi.hhkyselypalvelu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceOption;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceOptionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;

@Controller // Controller for questions made with thymeleaf
public class QuestionController {
	// jouni
	//
	@Autowired
	private OpenTextQuestionRepository questionrepo;
	@Autowired
	private QueryRepository queryrepo;
	@Autowired
	private MultipleChoiceQuestionRepository multichoicequestionrepo;


	// RECEIVES EMPTY QUESTION
	@GetMapping("/createquestions/{id}")
	public String getQuestionCreation(@PathVariable("id") Long id, Model model) {
		OpenTextQuestion textquestion = new OpenTextQuestion();
		MultipleChoiceQuestion multichoicequestion = new MultipleChoiceQuestion();
		Query query = queryrepo.findById(id).get();
		textquestion.setQuery(query);
		multichoicequestion.setQuery(query);
		model.addAttribute("query", query);
		model.addAttribute("question", textquestion);
		model.addAttribute("questions", query.getTextQuestions());
		model.addAttribute("multichoisequestion", multichoicequestion);
		model.addAttribute("multichoisequestions", query.getChoiceQuestions());
		return "createquestion";
	}

	// save questions
	@PostMapping("/savequestion")
	public String saveQuestiontest(@RequestParam String questiontype, @RequestParam String questiontext,
			@ModelAttribute Query query, Model model) {
		
			//save opentext question
		if (questiontype.equals("opentext") == true) {
			OpenTextQuestion textQuestion = new OpenTextQuestion(questiontext, query);
			questionrepo.save(textQuestion);
			return "redirect:/createquestions/" + textQuestion.getQuery().getId(); // redirect to add new questions
			
			//save checkbox question
		}if (questiontype.equals("checkbox") == true){
			MultipleChoiceQuestion checkboxQuestion = new MultipleChoiceQuestion();
			checkboxQuestion.setQuestion(questiontext);
			checkboxQuestion.setQuery(query);
			checkboxQuestion.setCheckbox(true);
			multichoicequestionrepo.save(checkboxQuestion);
			MultipleChoiceOption option = new MultipleChoiceOption();
			option.setQuestion(checkboxQuestion);
			model.addAttribute("option", option);
			return "addmultioptions"; // open new page to add options for multioption question
			
			//save multioption question
		}if (questiontype.equals("multioption") == true){
			MultipleChoiceQuestion multiQuestion = new MultipleChoiceQuestion();
			multiQuestion.setQuestion(questiontext);
			multiQuestion.setQuery(query);
			multichoicequestionrepo.save(multiQuestion);
			MultipleChoiceOption option = new MultipleChoiceOption();
			option.setQuestion(multiQuestion);
			model.addAttribute("option", option);
			return "addmultioptions"; // open new page to add options for multioption question
		}
		return "error";
	}

	
	// Deletes multioption question
	@GetMapping("/deletemultichoicequestion/{id}")
	public String deleteMultiChoiceQuestion(@PathVariable(name = "id") Long questionId) {
		MultipleChoiceQuestion multiQ = multichoicequestionrepo.findById(questionId)
		.orElseThrow(() -> new IllegalArgumentException("Invalid question Id:" + questionId));
		multichoicequestionrepo.deleteById(questionId);
		return "redirect:/createquestions/" + multiQ.getQuery().getId();
	}
	// Deletes text question
		@GetMapping("/deletetextquestion/{id}")
		public String deleteTextQuestion(@PathVariable(name = "id") Long questionId) {
			OpenTextQuestion textQ = questionrepo.findById(questionId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid question Id:" + questionId));
			questionrepo.deleteById(questionId);
			return "redirect:/createquestions/" + textQ.getQuery().getId();
		}
}
