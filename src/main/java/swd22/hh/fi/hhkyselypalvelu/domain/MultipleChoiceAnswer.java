package swd22.hh.fi.hhkyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MultipleChoiceAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	private String answer;
	
	private OpenTextQuestion question;
	
}
