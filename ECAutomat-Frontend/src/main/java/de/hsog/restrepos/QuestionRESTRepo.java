package de.hsog.restrepos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.hsog.dto.Category;
import de.hsog.dto.Question;
import de.hsog.dto.QuestionType;
import de.hsog.errors.BackendServerEntityNotFoundException;
import de.hsog.errors.BackendServerInternalErrorException;
import de.hsog.errors.ErrorHandler;

public class QuestionRESTRepo {
	
	private final String serverAddress;
	private final int serverPort;
	private final String restContext;
	private final RestTemplate template;
	private ErrorHandler handler;
	
	public QuestionRESTRepo() {
		this.serverAddress = "http://127.0.0.1";
		this.serverPort = 8888;
		this.restContext = "api/v1/REST";
		this.template = new RestTemplate();
		this.handler = new ErrorHandler();
	}
	
	public QuestionRESTRepo(String address, int port, String restContext) {
		this.serverAddress = address;
		this.serverPort = port;
		this.restContext = restContext;
		this.template = new RestTemplate();
	} 
	
	public List<Question> getAllUsers() throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		ResponseEntity<Question[]> response;
		String apiLink = String.format("%s:%d/%s/Questions", this.serverAddress, this.serverPort, this.restContext); 
		response = this.template.getForEntity(apiLink, Question[].class);
		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
		return Arrays.asList(response.getBody());
	}
	
	public Question getQuestionById(int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		String apiLink = String.format("%s:%d/%s/Questions/%d", this.serverAddress, this.serverPort, this.restContext, id); 
		ResponseEntity<Question> response = this.template.getForEntity(apiLink, Question.class);
		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
		return response.getBody();
	}
	
	public Question addQuestion(String questionName, int points, Category category) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		Question question = new Question(questionName, points, category, QuestionType.SIMPLE);
		HttpEntity<Question> request = new HttpEntity<Question>(question);
		String apiLink = String.format("%s:%d/%s/Questions", this.serverAddress, this.serverPort, this.restContext);
		ResponseEntity<Question> response = this.template.postForEntity(apiLink, request, Question.class);
		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
		return response.getBody();
	}
	
	public String updateQuestion(String newQuestionContent, int newQuestionPoints, int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		Question question = this.getQuestionById(id);
		question.setContent(newQuestionContent);
		question.setPoints(newQuestionPoints);
		HttpEntity<Question> request = new HttpEntity<Question>(question);
		String apiLink = String.format("%s:%d/%s/Questions/%d", this.serverAddress, this.serverPort, this.restContext, id);
		this.template.put(apiLink, request, Question.class);
		return apiLink;
	}
	
	public String deleteQuestion(int id) {
		String apiLink = String.format("%s:%d/%s/Questions/%d", this.serverAddress, this.serverPort, this.restContext, id);
		this.template.delete(apiLink);
		return apiLink;
	}
}
