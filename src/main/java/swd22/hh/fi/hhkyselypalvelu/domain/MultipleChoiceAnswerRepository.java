package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MultipleChoiceAnswerRepository extends CrudRepository<MultipleChoiceAnswer, Long>{
	List<MultipleChoiceAnswer> findByQuestion(MultipleChoiceQuestion question);
}
