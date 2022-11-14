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


//query which contains multiple questions, users can create queries and answer them
@Entity
public class Query {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "queryId")
	private Long id;
	private String title;
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "query")
	@JsonIgnoreProperties("query")
	private List<OpenTextQuestion> textQuestions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "query")
	@JsonIgnoreProperties("query")
	private List<MultipleChoiceQuestion> choiceQuestions;
	
	
	//CONSTRUCTORS
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Query(String queryTitle, String description) {
		super();
		this.title = queryTitle;
		this.description = description;
	}
	
	//SETTERS
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTextQuestions(List<OpenTextQuestion> textQuestions) {
		this.textQuestions = textQuestions;
	}
	public void setChoiceQuestions(List<MultipleChoiceQuestion> choiceQuestions) {
		this.choiceQuestions = choiceQuestions;
	}

	
	
	

	//GETTERS
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public List<OpenTextQuestion> getTextQuestions() {
		return textQuestions;
	}
	public List<MultipleChoiceQuestion> getChoiceQuestions() {
		return choiceQuestions;
	}

	
	
	//TOSTRING
	@Override
	public String toString() {
		return "Query [queryId=" + id + ", queryTitle=" + title + ", description=" + description +"]";
	}

	//adds one open or choice question to query
	public void addTextQuestion(OpenTextQuestion question) {
		this.textQuestions.add(question);
	}
	public void addMultipleChoiceQuestion(MultipleChoiceQuestion question) {
		this.choiceQuestions.add(question);
	}
	
	
}
