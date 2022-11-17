package swd22.hh.fi.hhkyselypalvelu.web;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;

@CrossOrigin
@Controller
public class RestServiceQueryController {
	@Autowired
	private QueryRepository repository;
	
	// RESTful service to get all queries
    @RequestMapping(value="/queries", method = RequestMethod.GET)
    public @ResponseBody List<Query> getQueriesRest() {
        return (List<Query>) repository.findAll();
    }    

	// RESTful service to get questions by id
    @RequestMapping(value="/queries/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Query> findQueryRest(@PathVariable("id") Long qId) {	
    	return repository.findById(qId);
    } 
    
    // RESTful service to save new question
    @RequestMapping(value="/queries", method = RequestMethod.POST)
    public @ResponseBody Query saveQueryRest(@RequestBody Query query) {	
    	return repository.save(query);
    }
    //get all opentextquestions
    @RequestMapping(value="/queries/{id}/opentextquestions" , method = RequestMethod.GET)
    public @ResponseBody List<OpenTextQuestion> answerQuestion(@PathVariable("id") Long queryId) {
    	List<OpenTextQuestion> questions = repository.findById(queryId).get().getTextQuestions();
    	return questions;
    }
    //get one open text questions
    @RequestMapping(value="/queries/{id}/opentextquestions/{qid}" , method = RequestMethod.GET)
    public @ResponseBody OpenTextQuestion getOpenTextQuestion(@PathVariable("id") Long queryId, @PathVariable("qid") int questionId) {
    	List<OpenTextQuestion> questions = repository.findById(queryId).get().getTextQuestions();
    	OpenTextQuestion question = questions.get(questionId-1);
    	return question;
    }
    /*
    @RequestMapping(value="/queries/{id}/opentextquestions/{qid}" , method = RequestMethod.POST)
    public @ResponseBody OpenTextAnswer addAnswerToQuestion(@PathVariable("id") Long queryId, @PathVariable("qid") int questionId,
    @RequestBody OpenTextAnswer answer){
    	List<OpenTextQuestion> questions = repository.findById(queryId).get().getTextQuestions();
    	OpenTextQuestion question = questions.get(questionId-1);
    	List<OpenTextAnswer> answers = question.getAnswers();
    	questions.get(questionId).addAnswer(answer);
		return repository.save(questions);
    	
    }*/
}
