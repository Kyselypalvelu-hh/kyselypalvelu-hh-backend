package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OpenTextAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	private String answer;
	
	@ManyToOne
	@JoinColumn(name="questionId")
	private OpenTextQuestion question;
	
	
	//Constructors
	public OpenTextAnswer(Long answerId, String answer, OpenTextQuestion question) {
		super();
		this.answerId = answerId;
		this.answer = answer;
		this.question = question;
	}

	public OpenTextAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	//setters
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setQuestion(OpenTextQuestion question) {
		this.question = question;
	}

	//getters
	public Long getAnswerId() {
		return answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public OpenTextQuestion getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "OpenTextAnswer [answerId=" + answerId + ", answer=" + answer + ", question=" + question + "]";
	}
	
	
	
}
