package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QueryService {
	@Autowired QueryRepository repo;
	
	public List<Query> listAll(String keyword) {
        if (keyword != null) {
        	return repo.search(keyword);
        }
        return (List<Query>) repo.findAll();
	}
}
