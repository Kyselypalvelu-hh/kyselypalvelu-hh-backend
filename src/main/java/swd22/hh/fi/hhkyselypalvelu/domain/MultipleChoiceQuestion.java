package swd22.hh.fi.hhkyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Question which has multiple options to choose from as an answer, it can be checkbox(multiple answers) or radio(only 1 answer)
@Entity
public class MultipleChoiceQuestion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="questionId")
	private long questionId;
	private String question;
	private boolean isCheckbox;

	
	@ManyToOne
	@JoinColumn(name="queryId")
	@JsonIgnoreProperties("multipleChoiceQuestions")
	private Query query;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnoreProperties("question")
	private List<MultipleChoiceOption> choiceOptions;
	
	
	//ANSWERS
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnoreProperties("question")
	private List<MultipleChoiceAnswer> answers;
	
	public List<MultipleChoiceAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<MultipleChoiceAnswer> answers) {
		this.answers = answers;
	}

	
	//-----
	
	

	public List<MultipleChoiceOption> getChoiceOptions() {
		return choiceOptions;
	}

	public void setChoiceOptions(List<MultipleChoiceOption> choiceOptions) {
		this.choiceOptions = choiceOptions;
	}

	//Constructors
	public MultipleChoiceQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultipleChoiceQuestion(String title) {
		super();
		this.question = title;

	}

	//SETTERS
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public void setQuestion(String title) {
		this.question = title;
	}


	public void setQuery(Query query) {
		this.query = query;
	}
	public void setCheckbox(boolean isCheckbox) {
		this.isCheckbox = isCheckbox;
	}


	//GETTERS
	public long getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	
	public Query getQuery() {
		return query;
	}
	
	public boolean isCheckbox() {
		return isCheckbox;
	}

	

	//TOSTRING
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + question + ", query=" + query
				+ "]";
	}




}
