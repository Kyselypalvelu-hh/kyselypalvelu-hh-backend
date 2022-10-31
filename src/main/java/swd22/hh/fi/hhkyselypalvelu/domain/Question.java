package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long questionId;
	private String title;
	private String answer;
	
	@ManyToOne()
	private Query query;
	
	//Constructors
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(String title, String answer) {
		super();
		this.title = title;
		this.answer = answer;
	}

	//SETTERS
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

	public void setQuery(Query query) {
		this.query = query;
	}

	//GETTERS
	public long getQuestionId() {
		return questionId;
	}

	public String getTitle() {
		return title;
	}

	public String getAnswer() {
		return answer;
	}
	
	public Query getQuery() {
		return query;
	}

	//TOSTRING
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + title + ", answer=" + answer + "]";
	}
	
	
}
