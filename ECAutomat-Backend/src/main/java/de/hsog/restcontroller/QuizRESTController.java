package de.hsog.restcontroller;

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

import de.hsog.models.Quiz;
import de.hsog.repositories.QuizRepository;

@RestController
@RequestMapping(value = "/REST")
public class QuizRESTController{

	private final QuizRepository quizRepository;
	private final ObjectMapper mapper;
	
	
	/**
	 * CRUD based RESTController
	 * @param quizRepo
	 */
	public QuizRESTController(QuizRepository quizRepo) {
		this.quizRepository = quizRepo;
		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@GetMapping("Quizes")
	public ResponseEntity<String> quizes() {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			responseBody = this.mapper.writeValueAsString(this.quizRepository.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseBody, responseStatus);
	}
	
	@GetMapping("Quizes/{id}")
	public ResponseEntity<String> quizyById(@PathVariable int id) {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Quiz foundQuizy = this.quizRepository.findById(id).get();
			responseBody = this.mapper.writeValueAsString(foundQuizy);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}  catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	@PostMapping("Quizes")
	public ResponseEntity<String> addQuizy(@RequestBody Quiz quiz) {
		quiz.setId(null);
		Quiz savedObj = this.quizRepository.save(quiz);
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			responseBody = this.mapper.writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}

	@PutMapping("Quizes/{id}")
	public ResponseEntity<String> updateQuizy(@RequestBody Quiz quizInput, @PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Quiz quizDB = this.quizRepository.findById(id).get();
			quizDB.setMaxScore(quizInput.getMaxScore());
			quizDB.setStringPlayDate(quizInput.getPlaydate().toString());
			quizDB.setPlayer(quizInput.getPlayer());
			Quiz savedObj = this.quizRepository.save(quizDB);
			responseBody = this.mapper.writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	@DeleteMapping("Quizes/{id}")
	public ResponseEntity<String> deleteQuizyById(@PathVariable int id) {
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
