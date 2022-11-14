package swd22.hh.fi.hhkyselypalvelu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceAnswer;
import swd22.hh.fi.hhkyselypalvelu.domain.MultipleChoiceAnswerRepository;
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
	
	public static List<MultipleChoiceOption> oneToFive(MultipleChoiceQuestion question){
		List<MultipleChoiceOption> options = new ArrayList<>();
		for (int i = 1; i<=5;i++) {
			String option = ""+i;
			options.add(new MultipleChoiceOption(option,question));
		}
		return options;
	}
	
	@Bean
	public CommandLineRunner launchTestQuery (QueryRepository queryRepo, OpenTextQuestionRepository textRepo,
			MultipleChoiceQuestionRepository choiceRepo, MultipleChoiceOptionRepository optionRepo,OpenTextAnswerRepository textAnswerRepo,MultipleChoiceAnswerRepository choiceAnswerRepo) {
		return (args) ->{
			
			Query query = new Query("Orientaatioviikot 2022","Vuonna 2022 aloittaineille opiskelijoille suunnattu kysely orientaatioviikoista");
			
			//Textquestions
			List<OpenTextQuestion> textQuestions = new ArrayList<>();
			OpenTextQuestion textQuestion1 = new OpenTextQuestion("Mikä oli epäselvintä tutustumisessa?",query);
			OpenTextQuestion textQuestion2 = new OpenTextQuestion("Vapaa sana.",query);
			textQuestions.add(textQuestion1);
			textQuestions.add(textQuestion2);
			query.setTextQuestions(textQuestions);
			
			//MultipleChoices
			List<MultipleChoiceQuestion> choiceQuestions = new ArrayList<>();
			
			MultipleChoiceQuestion question1 = new MultipleChoiceQuestion();
			question1.setCheckbox(false);
			question1.setQuestion("Aloititko opiskelun");
			question1.setQuery(query);
			List<MultipleChoiceOption> options1 = new ArrayList<>();
			options1.add(new MultipleChoiceOption("Keväällä 2022",question1));
			options1.add(new MultipleChoiceOption("Syksyllä 2022",question1));
			question1.setChoiceOptions(options1);
			choiceQuestions.add(question1);
			
			MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
			question2.setCheckbox(false);
			question2.setQuestion("Onko sinulla aiempaa opiskelukokemusta (amk/yliopisto)");
			question2.setQuery(query);
			List<MultipleChoiceOption> options2 = new ArrayList<>();
			options2.add(new MultipleChoiceOption("Kyllä",question2));
			options2.add(new MultipleChoiceOption("Ei",question2));
			question2.setChoiceOptions(options2);
			choiceQuestions.add(question2);
			
			
			//1-5 option questions
			String[] questionList = {"Arvio (1-5) Kuinka hyvin ensimmäisten päivien tutustuminen kampukseen auttoi opiskelujen aloittamisessa.",
					"Tutoreihin lähestyminen ja niiltä kysyminen oli helppoa arvio(1-5)",
					"MyNet ja muihin opiskelualustoihin tutustuminen oli kattavaa",
					"Aloittavat opiskelukaverit vaikuttavat mukavilta"};
			
			for(int i = 0; i<questionList.length;i++) {
				MultipleChoiceQuestion question = new MultipleChoiceQuestion();
				question.setCheckbox(false);
				question.setQuestion(questionList[i]);
				question.setQuery(query);
				question.setChoiceOptions(oneToFive(question));
				
				choiceQuestions.add(question);
			}
			
			query.setChoiceQuestions(choiceQuestions);
			query.setTextQuestions(textQuestions);
			queryRepo.save(query);
			
		};
	}

	
	/*
	@Bean
	public CommandLineRunner launchTestQuery (QueryRepository queryRepo, OpenTextQuestionRepository textRepo,
			MultipleChoiceQuestionRepository choiceRepo, MultipleChoiceOptionRepository optionRepo,OpenTextAnswerRepository textAnswerRepo,MultipleChoiceAnswerRepository choiceAnswerRepo) {
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
			choiceQuestion.setCheckbox(true);
			choiceQuestion.setQuery(query1);
			
			
			//OPTIONS FOR QUESTION
			//CREATE LIST OF OPTIONS
			List<MultipleChoiceOption> options = new ArrayList<>();
			
			//OPTION 1
			MultipleChoiceOption option1 = new MultipleChoiceOption();
			option1.setOption("Sininen");
			option1.setQuestion(choiceQuestion);
			
			//OPTION 2
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
			
			
			//multiple choice answer
			MultipleChoiceAnswer cAnswer1 = new MultipleChoiceAnswer();
			List<MultipleChoiceOption> answerOptions = new ArrayList<>();
			answerOptions.add(option1);
			answerOptions.add(option2);
			cAnswer1.setOptions(answerOptions);
			cAnswer1.setQuestion(choiceQuestion);
			
			choiceAnswerRepo.save(cAnswer1);
			
		};
	}

	// */
}
