package de.hsog.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import de.hsog.models.Quiz;
import de.hsog.models.Player;
import de.hsog.models.Question;
import de.hsog.repositories.QuizRepository;
import de.hsog.repositories.PlayerRepository;
import de.hsog.repositories.QuestionRepository;

@Controller
public class QuizController {

	private final QuizRepository quizRepository;
	private final PlayerRepository userRepository;
	private final QuestionRepository questionRepository;

	public QuizController(QuizRepository quizRepository, PlayerRepository userRepository, QuestionRepository questionRepo) {
		this.quizRepository = quizRepository;
		this.userRepository = userRepository;
		this.questionRepository = questionRepo;
	}

	@QueryMapping
	public Iterable<Quiz> quizes() {
		return this.quizRepository.findAll();
	}

	@QueryMapping
	public Optional<Quiz> quizById(@Argument Integer id) {
		return this.quizRepository.findById(id);
	}

	record QuizInput(String name, String playDate, Integer maxScore, Integer playerID) {
	}
	
	@MutationMapping
	public Quiz addQuestionToQuiz(@Argument Integer quizID, @Argument Integer questionID) {
		Quiz quiz = this.quizRepository.findById(quizID).orElseThrow(() -> new IllegalArgumentException("Quiz is null"));
		Question question = this.questionRepository.findById(questionID).orElseThrow(() -> new IllegalArgumentException("Question is null"));
		quiz.getQuestions().add(question);
		return this.quizRepository.save(quiz);
	}
	
	@MutationMapping
	public Quiz addQuiz(@Argument QuizInput quiz) {
		Player player = this.userRepository.findById(quiz.playerID()).orElseThrow(() -> new IllegalArgumentException());
		Quiz q = new Quiz(quiz.name(), quiz.playDate(), quiz.maxScore(), player);
		return this.quizRepository.save(q);
	}
	
	@MutationMapping
	public Quiz updateQuiz(@Argument Integer id, @Argument QuizInput newQuiz) {
		Player player = this.userRepository.findById(newQuiz.playerID()).orElseThrow(() -> new IllegalArgumentException());
		Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		quiz.setMaxScore(newQuiz.maxScore());
		quiz.setStringPlayDate(newQuiz.playDate());
		quiz.setPlayer(player);
		return this.quizRepository.save(quiz);
	}

	@MutationMapping
	public boolean deleteQuizById(@Argument Integer id) {
		// TODO: test delete
		try {
			this.quizRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@QueryMapping
	/**
	 * generates new Quiz with random questions, adds it to DB and returns it.
	 * 
	 * @return newly generated Quiz with random questions
	 */
	public Quiz generateRandomQuiz() {
		/* TODO: Generate Quiz with n-amount random Questions + add to schema.gql */
		
		return new Quiz();
	}
}
