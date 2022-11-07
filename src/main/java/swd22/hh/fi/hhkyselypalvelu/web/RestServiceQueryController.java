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
}
