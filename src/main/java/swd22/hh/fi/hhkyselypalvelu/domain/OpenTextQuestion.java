package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Question which receives open text answer
@Entity
public class OpenTextQuestion implements Question{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long questionId;
	private String question;
	
	@ManyToOne
	@JoinColumn(name="queryId")
	@JsonIgnoreProperties("textQuestions")
	private Query query;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnoreProperties("question")
	private List<OpenTextAnswer> answers;
	
	

	//Constructors
	public OpenTextQuestion() {

	}

	public OpenTextQuestion(String title,Query query) {
		super();
		this.question = title;
		this.query = query;

	}

	//SETTERS
	public void setAnswers(List<OpenTextAnswer> answers) {
		this.answers = answers;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public void setTitle(String title) {
		this.question = title;
	}


	public void setQuery(Query query) {
		this.query = query;
	}

	//GETTERS
	public Long getQuestionId() {
		return questionId;
	}

	public String getTitle() {
		return question;
	}

	
	public Query getQuery() {
		return query;
	}
	
	public List<OpenTextAnswer> getAnswers() {
		return answers;
	}
	

	//TOSTRING
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + question + ", query=" + query
				+ "]";
	}
	
	//adds one answer to question
	public void addAnswer(OpenTextAnswer answer) {
		this.answers.add(answer);
	}
	
	//INTERFACE
	public String getQuestionIf() {
		return question;
	}
	public String getQuestionType() {
		return "text";
	}
	
	
}
