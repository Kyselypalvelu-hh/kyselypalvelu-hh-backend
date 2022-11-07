package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OpenTextQuestionRepository extends CrudRepository<OpenTextQuestion, Long>{
	
	List<OpenTextQuestion> findByTitle(String title);
}
