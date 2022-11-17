package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

public class RestAnswerFormatter {
	private List<OpenTextAnswer> textAnswer;
	private List<MultipleChoiceAnswer> choiceAnswer;
	
	
	public RestAnswerFormatter(List<OpenTextAnswer> textAnswer, List<MultipleChoiceAnswer> choiceAnswer) {
		super();
		this.textAnswer = textAnswer;
		this.choiceAnswer = choiceAnswer;
	}
	public RestAnswerFormatter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<OpenTextAnswer> getTextAnswer() {
		return textAnswer;
	}
	public void setTextAnswer(List<OpenTextAnswer> textAnswer) {
		this.textAnswer = textAnswer;
	}
	public List<MultipleChoiceAnswer> getChoiceAnswer() {
		return choiceAnswer;
	}
	public void setChoiceAnswer(List<MultipleChoiceAnswer> choiceAnswer) {
		this.choiceAnswer = choiceAnswer;
	}
	
	
	@Override
	public String toString() {
		return "RestAnswerFormatter [textAnswer=" + textAnswer + ", choiceAnswer=" + choiceAnswer + "]";
	}
	
	
	
}
