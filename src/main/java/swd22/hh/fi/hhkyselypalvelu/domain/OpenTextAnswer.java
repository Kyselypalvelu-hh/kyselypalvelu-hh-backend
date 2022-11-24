package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//A single answer to one OpenTextQuestion
@Entity
public class OpenTextAnswer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	private String answer;
	
	@ManyToOne
	@JoinColumn(name="questionId")
	@JsonIgnoreProperties({"query","answers"})
	private OpenTextQuestion question;
	
	
	//Constructors
	public OpenTextAnswer(String answer, OpenTextQuestion question) {
		super();
		this.answer = answer;
		this.question = question;
	}

	public OpenTextAnswer() {

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

	//Tostring
	@Override
	public String toString() {
		return "OpenTextAnswer [answerId=" + answerId + ", answer=" + answer + ", question=" + question + "]";
	}
	
	
	//TESTING INTERFACE
	public String returnStringTest() {
		return "";
	}
	
	
	
}
