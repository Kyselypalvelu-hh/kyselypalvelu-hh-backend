package swd22.hh.fi.hhkyselypalvelu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.Question;
import swd22.hh.fi.hhkyselypalvelu.domain.QuestionRepository;

@SpringBootApplication
public class HhKyselypalveluApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner launchTestQuery (QueryRepository queryRepo, QuestionRepository questionRepo) {
		return (args) ->{
			Query query1 = new Query();
			query1.setQueryTitle("Henkilötiedot");
			
			Question question1 = new Question();
			question1.setTitle("Etunimi");
			
			Question question2 = new Question();
			question2.setTitle("Sukunimi");
			
			Question question3 = new Question();
			question3.setTitle("Kotipaikkakunta");
			
			List<Question> questions = new ArrayList<>();
			questions.add(question1);
			questions.add(question2);
			questions.add(question3);
			
			query1.setQuestions(questions);
			for (Question question: questions) {
				questionRepo.save(question);
			}
			
			queryRepo.save(query1);
			
			
		};
	}

}
