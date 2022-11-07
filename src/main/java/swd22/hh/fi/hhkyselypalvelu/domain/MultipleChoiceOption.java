package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class MultipleChoiceOption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long optionId;
	private String option;
	
	@ManyToOne
	@JoinColumn(name="questionId")
	@JsonIgnoreProperties("textQuestions")
	private	MultipleChoiceQuestion question;
	
	
	//CONSTRUCTORS
	public MultipleChoiceOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipleChoiceOption(Long optionId, String option, MultipleChoiceQuestion question) {
		super();
		this.optionId = optionId;
		this.option = option;
		this.question = question;
	}
	
	
	//SETTERS
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public void setQuestion(MultipleChoiceQuestion question) {
		this.question = question;
	}
	
	
	//GETTERS
	public Long getOptionId() {
		return optionId;
	}

	public String getOption() {
		return option;
	}

	public MultipleChoiceQuestion getQuestion() {
		return question;
	}

	
	//TOSTRING
	@Override
	public String toString() {
		return "MultipleChoiceOption [optionId=" + optionId + ", option=" + option + ", question=" + question + "]";
	}
	
	
	
	

}
