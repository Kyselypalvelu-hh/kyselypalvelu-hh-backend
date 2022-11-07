package swd22.hh.fi.hhkyselypalvelu.domain;

import org.springframework.data.repository.CrudRepository;

public interface MultipleChoiceQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Long>{

	MultipleChoiceQuestion findByQuestion(String question);
}
