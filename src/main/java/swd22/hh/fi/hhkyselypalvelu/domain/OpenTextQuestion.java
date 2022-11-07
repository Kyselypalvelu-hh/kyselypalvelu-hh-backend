package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OpenTextQuestion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long questionId;
	private String question;

	
	@ManyToOne
	@JoinColumn(name="queryId")
	@JsonIgnoreProperties("textQuestions")
	private Query query;
	
	//Constructors
	public OpenTextQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OpenTextQuestion(String title) {
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

	

	//TOSTRING
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + question + ", query=" + query
				+ "]";
	}
	
	
}
