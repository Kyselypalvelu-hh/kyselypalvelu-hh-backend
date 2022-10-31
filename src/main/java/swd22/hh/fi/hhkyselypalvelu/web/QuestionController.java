package swd22.hh.fi.hhkyselypalvelu.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import swd22.hh.fi.hhkyselypalvelu.domain.Question;
import swd22.hh.fi.hhkyselypalvelu.domain.QuestionRepository;

@CrossOrigin
@Controller
public class QuestionController {
	@Autowired
	private QuestionRepository repository;
	
	// RESTful service to get all questions
    @RequestMapping(value="/questions", method = RequestMethod.GET)
    public @ResponseBody List<Question> getQuestionsRest() {	
        return (List<Question>) repository.findAll();
    }    

	// RESTful service to get questions by id
    @RequestMapping(value="/questions/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Question> findQuestionRest(@PathVariable("id") Long qId) {	
    	return repository.findById(qId);
    } 
    
    // RESTful service to save new question
    @RequestMapping(value="/questions", method = RequestMethod.POST)
    public @ResponseBody Question saveQuestionsRest(@RequestBody Question question) {	
    	return repository.save(question);
    }
}
