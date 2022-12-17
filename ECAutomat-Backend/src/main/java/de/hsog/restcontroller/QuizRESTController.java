package de.hsog.restcontroller;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.hsog.models.Question;
import de.hsog.models.Quiz;
import de.hsog.repositories.PlayerRepository;
import de.hsog.repositories.QuestionRepository;
import de.hsog.repositories.QuizRepository;

@RestController
@RequestMapping(value = "/REST")
public class QuizRESTController{

	private final QuizRepository quizRepository;
	private final QuestionRepository questionRepository;
	private final PlayerRepository playerRepository;
	private final ObjectMapper mapper;
	
	
	/**
	 * CRUD based RESTController
	 * @param quizRepo
	 */
	public QuizRESTController(QuizRepository quizRepo, QuestionRepository questRepo, PlayerRepository playerRepo) {
		this.quizRepository = quizRepo;
		this.questionRepository = questRepo;
		this.playerRepository = playerRepo;
		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@GetMapping("Quizes")
	public ResponseEntity<String> quizes() {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.quizRepository.findAll());
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseBody, responseStatus);
	}
	
	@GetMapping("Quizes/{id}")
	public ResponseEntity<String> quizById(@PathVariable int id) {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Quiz foundQuizy = this.quizRepository.findById(id).get();
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundQuizy);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}  catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	record QuizInput(String name, String playDate, Integer maxScore, Integer playerID) {}
	
	@PostMapping("Quizes")
	public ResponseEntity<String> addQuiz(@RequestBody QuizInput q) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Quiz quiz = new Quiz(
					q.name(),
					q.playDate(),
					q.maxScore(),
					this.playerRepository.findById(q.playerID()).get()
					);

			Quiz savedObj = this.quizRepository.save(quiz);
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	record QuizHasQuestion(Integer questionId) {}
	
	@PostMapping("Quizes/{id}")
	public ResponseEntity<String> addQuestionToQuiz(@RequestBody QuizHasQuestion q, @PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Quiz quiz = this.quizRepository.findById(id).get();
			Question question = this.questionRepository.findById(q.questionId()).get();
			quiz.getQuestions().add(question);
			responseBody = this.mapper.writeValueAsString(this.quizRepository.save(quiz));
		} catch (NoSuchElementException e) {
			responseBody = "Quiz not found on Index: " + id;
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (DateTimeParseException e) {
			responseBody = "Required PlayDate Format: yyyy-mm-dd hh:mm";
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	

	@PutMapping("Quizes/{id}")
	public ResponseEntity<String> updateQuiz(@RequestBody Quiz quizInput, @PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Quiz quizDB = this.quizRepository.findById(id).get();
			quizDB.setMaxScore(quizInput.getMaxScore());
			quizDB.setStringPlayDate(quizInput.getPlayDate().toString());
			Quiz savedObj = this.quizRepository.save(quizDB);
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	@DeleteMapping("Quizes/Questions/{id}")
	public ResponseEntity<String> deleteQuestionFromQuiz() {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	@DeleteMapping("Quizes/{id}")
	public ResponseEntity<String> deleteQuizById(@PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.quizRepository.deleteById(id);
			responseBody = "{\"status\": 204}";
		} catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
}
