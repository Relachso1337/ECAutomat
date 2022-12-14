package de.hsog.restcontroller;

import java.util.List;
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

import de.hsog.models.MultipleChoiceQuestion;
import de.hsog.models.Question;
import de.hsog.models.QuestionType;
import de.hsog.models.SimpleQuestion;
import de.hsog.repositories.CategoryRepository;
import de.hsog.repositories.QuestionRepository;

@RestController
@RequestMapping(value = "/REST")
public class QuestionRESTController{

	private final QuestionRepository questionRepository;
	private final CategoryRepository categoryRepository;
	private final ObjectMapper mapper;
	
	
	/**
	 * CRUD based RESTController
	 * @param questionRepo
	 */
	public QuestionRESTController(QuestionRepository questionRepo, CategoryRepository categoryRepo) {
		this.questionRepository = questionRepo;
		this.categoryRepository = categoryRepo;
		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@GetMapping(value="Questions", produces = "application/json")
	public ResponseEntity<String> questions() {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.questionRepository.findAll());
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseBody, responseStatus);
	}
	
	@GetMapping(value="Questions/{id}", produces = "application/json")
	public ResponseEntity<String> questionyById(@PathVariable int id) {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Question foundQuestiony = this.questionRepository.findById(id).get();
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundQuestiony);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}  catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	record QuestionInput(String content, Integer points, Integer categoryID, String questionType, boolean isCorrect) {};
	
	@PostMapping(value="Questions", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addQuestion(@RequestBody QuestionInput q) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			Question question;
			if (q.questionType != null && q.questionType.toUpperCase().equals("MULTIPLECHOICE")) {
				question = new MultipleChoiceQuestion(
						q.content, 
						q.points, 
						this.categoryRepository.
											findById(q.categoryID()).get()); 
			}  else {
				question = new SimpleQuestion(
						q.content(), 
						q.points(), 
						this.categoryRepository.findById(q.categoryID()).get(), q.isCorrect);	
			}
			Question savedObj = this.questionRepository.save(question);
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	/**
	 * Adds multiple Questions at once
	 * @param inputlist
	 * @return
	 */
	@PostMapping(value="Questions/all", consumes="application/json", produces="application/json")
	public ResponseEntity<String> addAllQuestions(@RequestBody List<QuestionInput> inputlist) {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			for (QuestionInput qInput : inputlist) {
				Integer points = qInput.points;
				if (points == null) {
					points = 100;
				}
				this.addQuestion(new QuestionInput(qInput.content, points, qInput.categoryID, qInput.questionType, qInput.isCorrect));
			}
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<>(responseBody, responseStatus);		
	}
	
	

	@PutMapping(value="Questions/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateQuestiony(@RequestBody Question questionInput, @PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Question questionDB = this.questionRepository.findById(id).get();
			questionDB.setContent(questionInput.getContent());
			questionDB.setPoints(questionInput.getPoints());
			Question savedObj = this.questionRepository.save(questionDB);
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
	
	@DeleteMapping(value="Questions/{id}", produces = "application/json")
	public ResponseEntity<String> deleteQuestionyById(@PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.questionRepository.deleteById(id);
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
