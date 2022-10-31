package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;

@Entity
public class Question {

	private long questionId;
	private String title;
	private String answer;
	
	//Constructors
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(long questionId, String title, String answer) {
		super();
		this.questionId = questionId;
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

	//TOSTRING
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + title + ", answer=" + answer + "]";
	}
	
	
}
