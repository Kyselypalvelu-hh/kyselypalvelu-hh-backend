package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

//FOR SHOWING ANSWERS IN REACT
public class RestShowAnswersFormatter {
	
	private List<OpenTextAnswer> textAnswer;
	private List<MultipleChoiceAnswer> choiceAnswer;
	private Question question;
	
	public RestShowAnswersFormatter(List<OpenTextAnswer> textAnswer, List<MultipleChoiceAnswer> choiceAnswer) {
		super();
		this.textAnswer = textAnswer;
		this.choiceAnswer = choiceAnswer;
	}
	
	
	
	public RestShowAnswersFormatter(List<OpenTextAnswer> textAnswer, List<MultipleChoiceAnswer> choiceAnswer,
			Question question) {
		super();
		this.textAnswer = textAnswer;
		this.choiceAnswer = choiceAnswer;
		this.question = question;
	}



	public RestShowAnswersFormatter() {
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
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	

}
