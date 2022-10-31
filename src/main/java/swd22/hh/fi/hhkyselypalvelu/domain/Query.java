package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Query {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long queryId;
	private String queryTitle;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "query")
	@JoinColumn(name="queryId")
	private List<Question> questions;
	
	//CONSTRUCTORS
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Query(String queryTitle) {
		super();
		this.queryTitle = queryTitle;
	}
	
	//SETTERS
	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}
	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}
	
	//GETTERS
	public Long getQueryId() {
		return queryId;
	}
	public String getQueryTitle() {
		return queryTitle;
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return "Query [queryId=" + queryId + ", queryTitle=" + queryTitle + "]";
	}
	
	
}
