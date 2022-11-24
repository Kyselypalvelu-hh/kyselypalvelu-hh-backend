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
			
			//answer
			OpenTextAnswer answer1 = new OpenTextAnswer("en tiedä",textQuestion1);
			OpenTextAnswer answer2 = new OpenTextAnswer("Tutor ei puhunut suomea",textQuestion2);
			List<MultipleChoiceOption> answer3o = new ArrayList<>();
			answer3o.add(options1.get(1));
			MultipleChoiceAnswer answer3 = new MultipleChoiceAnswer(question1,answer3o);
			
			List<MultipleChoiceOption> answer4o = new ArrayList<>();
			answer4o.add(options2.get(0));
			MultipleChoiceAnswer answer4 = new MultipleChoiceAnswer(question2,answer4o);
			
			textAnswerRepo.save(answer1);
			textAnswerRepo.save(answer2);
			choiceAnswerRepo.save(answer3);
			choiceAnswerRepo.save(answer4);
			
			
			Query query2 = new Query("TESTTEST","testing");
			
			//Textquestions
			List<OpenTextQuestion> textQuestionsTest = new ArrayList<>();
			OpenTextQuestion textQuestion3 = new OpenTextQuestion("Mitä testataan?",query2);
			OpenTextQuestion textQuestion4 = new OpenTextQuestion("Testin Testi.",query2);
			textQuestionsTest.add(textQuestion3);
			textQuestionsTest.add(textQuestion4);
			query2.setTextQuestions(textQuestionsTest);
			
			//MultipleChoices TEST
			List<MultipleChoiceQuestion> choiceQuestionsTest = new ArrayList<>();
			
			MultipleChoiceQuestion questionTest1 = new MultipleChoiceQuestion();
			questionTest1.setCheckbox(false);
			questionTest1.setQuestion("Koska testasit");
			questionTest1.setQuery(query2);
			List<MultipleChoiceOption> optionsTest1 = new ArrayList<>();
			optionsTest1.add(new MultipleChoiceOption("TestiKevät",questionTest1));
			optionsTest1.add(new MultipleChoiceOption("TestiSyksy",questionTest1));
			questionTest1.setChoiceOptions(optionsTest1);
			choiceQuestionsTest.add(questionTest1);
			
			MultipleChoiceQuestion questionTest2 = new MultipleChoiceQuestion();
			questionTest2.setCheckbox(false);
			questionTest2.setQuestion("Testaatko usein");
			questionTest2.setQuery(query2);
			List<MultipleChoiceOption> optionsTest2 = new ArrayList<>();
			optionsTest2.add(new MultipleChoiceOption("Kyllä",questionTest2));
			optionsTest2.add(new MultipleChoiceOption("Ei",questionTest2));
			questionTest2.setChoiceOptions(optionsTest2);
			choiceQuestions.add(questionTest2);
			
			
			//1-5 option questions
			String[] questionListTest = {"Arvio (1-5) Kuinka hyvin ensimmäisten päivien tutustuminen kampukseen auttoi opiskelujen aloittamisessa.",
					"Tutoreihin lähestyminen ja niiltä kysyminen oli helppoa arvio(1-5)",
					"MyNet ja muihin opiskelualustoihin tutustuminen oli kattavaa",
					"Aloittavat opiskelukaverit vaikuttavat mukavilta"};
			
			for(int i = 0; i<questionListTest.length;i++) {
				MultipleChoiceQuestion question = new MultipleChoiceQuestion();
				question.setCheckbox(false);
				question.setQuestion(questionList[i]);
				question.setQuery(query2);
				question.setChoiceOptions(oneToFive(question));
				
				choiceQuestionsTest.add(question);
			}
			
			query2.setChoiceQuestions(choiceQuestionsTest);
			query2.setTextQuestions(textQuestionsTest);
			queryRepo.save(query2);
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
