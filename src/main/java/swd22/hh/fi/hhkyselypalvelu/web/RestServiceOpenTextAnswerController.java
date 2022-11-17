package swd22.hh.fi.hhkyselypalvelu.web;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswerRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.RestAnswerFormatter;
@CrossOrigin
@Controller
public class RestServiceOpenTextAnswerController {
	@Autowired
	private OpenTextAnswerRepository answerRepository;
	
	@Autowired
	private OpenTextQuestionRepository questionRepository;
	
	// RESTful service to get all opentextanswers
    @RequestMapping(value="/opentextanswers", method = RequestMethod.GET)
    public @ResponseBody List<OpenTextAnswer> getOpenTextAnswersRest() {	
        return (List<OpenTextAnswer>) answerRepository.findAll();
    }    

	// RESTful service to get answers by question id

    
    
    @RequestMapping(value="/answers", method = RequestMethod.POST)
    public @ResponseBody RestAnswerFormatter saveAnswer(@RequestBody RestAnswerFormatter format) {	
    	List<OpenTextAnswer> openTextAnswers = format.getTextAnswer();
    	List<MultipleChoiceAnswer> multipleChoiseAnswer = format.getChoiceAnswer();
    	for(OpenTextAnswer answer: format.getTextAnswer()) {
    		answerRepository.save(answer);
    	}
    	
    	return format;
    }

}
