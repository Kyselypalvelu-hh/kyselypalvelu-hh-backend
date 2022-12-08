package swd22.hh.fi.hhkyselypalvelu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceOption;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceOptionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestionRepository;


@Controller // Controller for multiple choice OPTIONS made with thymeleaf
public class OptionController {
	
	@Autowired
	private MultipleChoiceQuestionRepository multichoicequestionrepo;

	@Autowired
	private MultipleChoiceOptionRepository multioptionrepo;
	
	// Shows form for creating options for a question
	@GetMapping("/createmultichoioptions/{id}")
	public String getMultipleChoiceAnswerCreation(@PathVariable("id") Long id, Model model) {
		MultipleChoiceOption option = new MultipleChoiceOption();
		MultipleChoiceQuestion multiquestion = multichoicequestionrepo.findById(id).get();
		option.setQuestion(multiquestion);
		model.addAttribute("option", option);
		return "addmultioptions";
	}

	// saves a single options for multichoice question
	@PostMapping("/saveoptions")
	public String saveOptionsForMultiChoiceQuestion(@ModelAttribute MultipleChoiceOption option, Model model) {
		multioptionrepo.save(option);
		return "redirect:/createmultichoioptions/" + option.getQuestion().getQuestionId();
	}
	// Deletes option from question
	@GetMapping("/deleteoption/{id}")
	public String deleteMultiChoiceOption(@PathVariable(name = "id") Long optionId) {
		MultipleChoiceOption option = multioptionrepo.findById(optionId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid option Id:" + optionId));
		multioptionrepo.deleteById(optionId);
		return "redirect:/createmultichoioptions/" + option.getQuestion().getQuestionId();
	}
	
}
