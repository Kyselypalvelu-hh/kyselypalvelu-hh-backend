package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface QueryRepository extends CrudRepository<Query, Long>{
	List<Query> findByTitle(String title);

	OpenTextAnswer save(List<OpenTextQuestion> questions);
}
