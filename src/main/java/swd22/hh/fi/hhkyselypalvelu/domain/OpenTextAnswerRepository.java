package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OpenTextAnswerRepository extends CrudRepository<OpenTextAnswer,Long>{

	List<OpenTextAnswer> findByQuestion(OpenTextQuestion question);
	
}
