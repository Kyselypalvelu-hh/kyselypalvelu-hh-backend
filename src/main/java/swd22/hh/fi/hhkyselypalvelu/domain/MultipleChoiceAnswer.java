package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MultipleChoiceAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	
	@ManyToOne
	@JoinColumn(name="questionId")
	private MultipleChoiceQuestion question;
	
	@ManyToMany
	private List<MultipleChoiceOption> options;

	
	//CONSTRUCTORS
	public MultipleChoiceAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipleChoiceAnswer(MultipleChoiceQuestion question, List<MultipleChoiceOption> options) {
		super();
		this.question = question;
		this.options = options;
	}

	//SETTERS
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public void setQuestion(MultipleChoiceQuestion question) {
		this.question = question;
	}

	public void setOptions(List<MultipleChoiceOption> options) {
		this.options = options;
	}

	//GETTERS
	public Long getAnswerId() {
		return answerId;
	}

	public MultipleChoiceQuestion getQuestion() {
		return question;
	}

	public List<MultipleChoiceOption> getOptions() {
		return options;
	}

	@Override
	public String toString() {
		return "MultipleChoiceAnswer [answerId=" + answerId + ", question=" + question + ", options=" + options + "]";
	}
	
	
	
	
	
	
	
}
