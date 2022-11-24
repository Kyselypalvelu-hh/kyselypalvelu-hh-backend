package swd22.hh.fi.hhkyselypalvelu.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceAnswerRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswerRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.RestAnswerFormatter;
import swd22.hh.fi.hhkyselypalvelu.domain.RestShowAnswersFormatter;
@CrossOrigin
@Controller
public class RestServiceAnswerController {
	@Autowired
	private OpenTextAnswerRepository textAnswerRepo;
	
	@Autowired
	private OpenTextQuestionRepository questionRepository;
	
	@Autowired
	private QueryRepository queryRepo;
	
	@Autowired
	private MultipleChoiceAnswerRepository choiceAnswerRepo;
	
	// RESTful service to get all opentextanswers
    @RequestMapping(value="/textanswers", method = RequestMethod.GET)
    public @ResponseBody List<OpenTextAnswer> getOpenTextAnswersRest() {	
        List<OpenTextAnswer> answers =  (List<OpenTextAnswer>) textAnswerRepo.findAll();
        return answers;
    }
    
    //get all answers to one query, for now handles only text questions
    @GetMapping("/queryanswers/{id}")
    public @ResponseBody List<RestShowAnswersFormatter> getAnswersForQuery(@PathVariable("id") Long id){
    	Query query = queryRepo.findById(id).get();
    	//to be returned
    	List<RestShowAnswersFormatter> answers = new ArrayList<>();
    	
    	//TODO	add handling for multiplechicequestions
    	for(OpenTextQuestion question: query.getTextQuestions()) {
    		List<OpenTextAnswer> answersForOneQuestion = textAnswerRepo.findByQuestion(question);
    		//Add answers to oner question to formatter which is added to list of formatters, empty array list is added for multiplechoicequestions for now
    		answers.add(new RestShowAnswersFormatter(answersForOneQuestion,new ArrayList<>(),question));
    	}
    	
    	for(MultipleChoiceQuestion question: query.getChoiceQuestions()) {
    		List<MultipleChoiceAnswer> answersForOne = choiceAnswerRepo.findByQuestion(question);
    		answers.add(new RestShowAnswersFormatter(new ArrayList<>(),answersForOne,question));
    	}
    	
    	return answers;
    }

	// RESTful service to get answers by question id
    @RequestMapping(value="/answers", method = RequestMethod.POST)
    public @ResponseBody RestAnswerFormatter saveAnswer(@RequestBody RestAnswerFormatter format) {
    	try {
			for(OpenTextAnswer answer: format.getTextAnswer()) {
				textAnswerRepo.save(answer);
			}
			return format;
		} catch (Exception e) {
			return format;
		}
    }

}
