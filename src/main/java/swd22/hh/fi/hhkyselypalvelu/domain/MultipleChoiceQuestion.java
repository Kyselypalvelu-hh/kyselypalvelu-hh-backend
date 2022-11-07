package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class MultipleChoiceQuestion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long questionId;
	private String question;
	private boolean isCheckbox;

	
	@ManyToOne
	@JoinColumn(name="queryId")
	@JsonIgnoreProperties("choiceQuestions")
	private Query query;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnoreProperties("question")
	private List<MultipleChoiceOption> choiceOptions;
	
	
	//Constructors
	public MultipleChoiceQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipleChoiceQuestion(String title) {
		super();
		this.question = title;

	}

	//SETTERS
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public void setTitle(String title) {
		this.question = title;
	}


	public void setQuery(Query query) {
		this.query = query;
	}
	public void setCheckbox(boolean isCheckbox) {
		this.isCheckbox = isCheckbox;
	}


	//GETTERS
	public long getQuestionId() {
		return questionId;
	}

	public String getTitle() {
		return question;
	}

	
	public Query getQuery() {
		return query;
	}
	
	public boolean isCheckbox() {
		return isCheckbox;
	}

	

	//TOSTRING
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + question + ", query=" + query
				+ "]";
	}




}