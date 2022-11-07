package swd22.hh.fi.hhkyselypalvelu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd22.hh.fi.hhkyselypalvelu.domain.Query;
import swd22.hh.fi.hhkyselypalvelu.domain.QueryRepository;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestion;
import swd22.hh.fi.hhkyselypalvelu.domain.OpenTextQuestionRepository;

@SpringBootApplication
public class HhKyselypalveluApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhKyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner launchTestQuery (QueryRepository queryRepo, OpenTextQuestionRepository questionRepo) {
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
				questionRepo.save(question);
			}
			
			
			
			
		};
	}

}
