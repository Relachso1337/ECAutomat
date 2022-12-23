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

import de.hsog.models.Suggestion;
import de.hsog.repositories.QuestionRepository;
import de.hsog.repositories.SuggestionRepository;

@RestController
@RequestMapping(value = "/REST")
public class SuggestionRESTController{

	private final SuggestionRepository suggestionRepository;
	private final QuestionRepository questionRepository;
	private final ObjectMapper mapper;
	
	
	/**
	 * CRUD based RESTController
	 * @param suggestionRepo
	 */
	public SuggestionRESTController(SuggestionRepository suggestionRepo, QuestionRepository questionRepo) {
		this.suggestionRepository = suggestionRepo;
		this.questionRepository  = questionRepo;
		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@GetMapping(value="Suggestions", produces="application/json")
	public ResponseEntity<String> suggestions() {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.suggestionRepository.findAll());
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseBody, responseStatus);
	}
	
	@GetMapping(value="Suggestions/{id}", produces="application/json")
	public ResponseEntity<String> suggestionyById(@PathVariable int id) {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Suggestion foundSuggestiony = this.suggestionRepository.findById(id).get();
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundSuggestiony);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}  catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	record SuggestionInput(String suggestionContent, Integer questionID, boolean isCorrect) {}
	
	@PostMapping(value="Suggestions", consumes="application/json", produces="application/json")
	public ResponseEntity<String> addSuggestiony(@RequestBody SuggestionInput s) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Suggestion suggestion = new Suggestion(
					s.suggestionContent(),
					this.questionRepository.findById(s.questionID()).get(),
					s.isCorrect()
				);
			Suggestion savedObj = this.suggestionRepository.save(suggestion);
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (NoSuchElementException e) {
			responseBody = "Question not Found";
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}

	@PutMapping(value="Suggestions/{id}", consumes="application/json", produces="application/json")
	public ResponseEntity<String> updateSuggestiony(@RequestBody Suggestion suggestionInput, @PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Suggestion suggestionDB = this.suggestionRepository.findById(id).get();
			suggestionDB.setSuggestionContent(suggestionInput.getSuggestionContent());
			suggestionDB.setCorrect(suggestionInput.isCorrect());
			Suggestion savedObj = this.suggestionRepository.save(suggestionDB);
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedObj);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (NoSuchElementException e) {
			responseBody = String.format("Suggestion on index: %d not found", id);
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	@DeleteMapping(value="Suggestions/{id}", produces="application/json")
	public ResponseEntity<String> deleteSuggestionyById(@PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.suggestionRepository.deleteById(id);
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
