package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QueryRepository extends CrudRepository<swd22.hh.fi.hhkyselypalvelu.domain.Query, Long>{
	List<swd22.hh.fi.hhkyselypalvelu.domain.Query> findByTitle(String title);
	
	@Query(value = "SELECT * from Query q WHERE CONCAT(q.title, ' ', q.description) LIKE %?1%",
			nativeQuery=true)
	public List<swd22.hh.fi.hhkyselypalvelu.domain.Query> search(String keyword);

}
