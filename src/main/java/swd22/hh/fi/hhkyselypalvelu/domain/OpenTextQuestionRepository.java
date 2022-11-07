package swd22.hh.fi.hhkyselypalvelu.domain;

import org.springframework.data.repository.CrudRepository;

public interface OpenTextQuestionRepository extends CrudRepository<OpenTextQuestion, Long>{
	
	OpenTextQuestion findByQuestion(String question);
}
