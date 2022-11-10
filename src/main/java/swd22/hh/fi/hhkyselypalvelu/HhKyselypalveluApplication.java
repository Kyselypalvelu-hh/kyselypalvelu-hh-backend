package swd22.hh.fi.hhkyselypalvelu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceOption;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceOptionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceQuestionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextAnswerRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;

@SpringBootApplication
public class HhKyselypalveluApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner launchTestQuery (QueryRepository queryRepo, OpenTextQuestionRepository textRepo,
			MultipleChoiceQuestionRepository choiceRepo, MultipleChoiceOptionRepository optionRepo,OpenTextAnswerRepository textAnswerRepo) {
		return (args) ->{
			
			Query query1 = new Query();
			query1.setTitle("Henkilötiedot");
			queryRepo.save(query1);
			
			OpenTextQuestion question1 = new OpenTextQuestion();
			question1.setTitle("Etunimi");
			question1.setQuery(query1);
			
			OpenTextQuestion question2 = new OpenTextQuestion();
			question2.setTitle("Sukunimi");
			question2.setQuery(query1);
			
			OpenTextQuestion question3 = new OpenTextQuestion();
			question3.setTitle("Kotipaikkakunta");
			question3.setQuery(query1);
			
			
			
			List<OpenTextQuestion> questions = new ArrayList<>();
			questions.add(question1);
			questions.add(question2);
			questions.add(question3);
			
			
			
			for (OpenTextQuestion question: questions) {
				textRepo.save(question);
			}
			

			//-----MULTIPLE CHOICE TEST DATA------
			
			//QUESTION: LEMPIVÄRI
			MultipleChoiceQuestion choiceQuestion = new MultipleChoiceQuestion();
			choiceQuestion.setQuestion("Lempiväri");
			choiceQuestion.setCheckbox(false);
			choiceQuestion.setQuery(query1);
			
			
			//OPTIONS FOR QUESTION
			//CREATE LIST OF OPTIONS
			List<MultipleChoiceOption> options = new ArrayList<>();
			
			//OPTION 1
			MultipleChoiceOption option1 = new MultipleChoiceOption();
			option1.setOption("Sininen");
			option1.setQuestion(choiceQuestion);
			
			//OPTION "
			MultipleChoiceOption option2 = new MultipleChoiceOption();
			option2.setOption("Vihreä");
			option2.setQuestion(choiceQuestion);
			
			//ADD OPTIONS TO LIST
			options.add(option1);
			options.add(option2);
			
			//SAVE TO DB
			choiceQuestion.setChoiceOptions(options);
			choiceRepo.save(choiceQuestion);
			
			
			
			//----ANSWERS-----
			
			OpenTextAnswer answer1 = new OpenTextAnswer("helsinki",question1);
			OpenTextAnswer answer2 = new OpenTextAnswer("Vantaa",question1);
			OpenTextAnswer answer3 = new OpenTextAnswer("Testitaavi",question2);
			
			textAnswerRepo.save(answer1);
			textAnswerRepo.save(answer2);
			textAnswerRepo.save(answer3);
			
			
		};
	}

}
