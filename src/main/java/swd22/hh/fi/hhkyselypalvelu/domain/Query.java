package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Query {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "queryId")
	private Long id;
	private String title;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "query")
	@JsonIgnoreProperties("query")
	private List<Question> questions;
	
	//CONSTRUCTORS
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Query(String queryTitle) {
		super();
		this.title = queryTitle;
	}
	
	//SETTERS
	public void setQueryId(Long queryId) {
		this.id = queryId;
	}
	public void setTitle(String queryTitle) {
		this.title = queryTitle;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	//GETTERS
	public Long getQueryId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return "Query [queryId=" + id + ", queryTitle=" + title + "]";
	}
	
	
}
