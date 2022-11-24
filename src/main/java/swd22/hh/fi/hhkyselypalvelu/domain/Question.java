package swd22.hh.fi.hhkyselypalvelu.domain;

public interface Question {
	//TITLE OF QUESTION
	public String getQuestionIf();
	//ID
	public Long getQuestionId();
	//TYPE, text/radio,checkbox
	public String getQuestionType();
}
