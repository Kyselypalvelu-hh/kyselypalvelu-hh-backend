package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class MultipleChoiceAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	
	@ManyToOne
	@JoinColumn(name="optionId")
	private MultipleChoiceOption option;
	
	private MultipleChoiceQuestion question;
	
	

	public MultipleChoiceAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipleChoiceAnswer(Long answerId, MultipleChoiceOption option) {
		super();
		this.answerId = answerId;
		this.option = option;
		this.question = option.getQuestion();
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public void setOption(MultipleChoiceOption option) {
		this.option = option;
		this.question = option.getQuestion();
	}

	public Long getAnswerId() {
		return answerId;
	}

	public MultipleChoiceOption getOption() {
		return option;
	}

	public MultipleChoiceQuestion getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "MultipleChoiceAnswer [answerId=" + answerId + ", option=" + option + ", question=" + question + "]";
	}

	
	//GETTERS SETTERS STRUCTORS
	
	
	
	
}
