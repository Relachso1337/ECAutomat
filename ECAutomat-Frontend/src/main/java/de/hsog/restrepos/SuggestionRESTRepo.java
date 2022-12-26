//package de.hsog.restrepos;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import de.hsog.dto.Question;
//import de.hsog.dto.Suggestion;
//import de.hsog.errors.BackendServerEntityNotFoundException;
//import de.hsog.errors.BackendServerInternalErrorException;
//import de.hsog.errors.ErrorHandler;
//
//public class SuggestionRESTRepo {
//	
//	private final String serverAddress;
//	private final int serverPort;
//	private final String restContext;
//	private final RestTemplate template;
//	private ErrorHandler handler;
//	
//	public SuggestionRESTRepo() {
//		this.serverAddress = "http://127.0.0.1";
//		this.serverPort = 8888;
//		this.restContext = "api/v1/REST";
//		this.template = new RestTemplate();
//		this.handler = new ErrorHandler();
//	}
//	
//	public SuggestionRESTRepo(String address, int port, String restContext) {
//		this.serverAddress = address;
//		this.serverPort = port;
//		this.restContext = restContext;
//		this.template = new RestTemplate();
//	} 
//	
//	public List<Suggestion> getAllUsers() throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		ResponseEntity<Suggestion[]> response;
//		String apiLink = String.format("%s:%d/%s/Suggestions", this.serverAddress, this.serverPort, this.restContext); 
//		response = this.template.getForEntity(apiLink, Suggestion[].class);
//		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
//		return Arrays.asList(response.getBody());
//	}
//	
//	public Suggestion getSuggestionById(int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		String apiLink = String.format("%s:%d/%s/Suggestions/%d", this.serverAddress, this.serverPort, this.restContext, id); 
//		ResponseEntity<Suggestion> response = this.template.getForEntity(apiLink, Suggestion.class);
//		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
//		return response.getBody();
//	}
//	
//	public Suggestion addSuggestion(String suggestionContent, Question question, boolean isCorrect) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		Suggestion suggestion = new Suggestion(suggestionContent, question, isCorrect);
//		HttpEntity<Suggestion> request = new HttpEntity<Suggestion>(suggestion);
//		String apiLink = String.format("%s:%d/%s/Suggestions", this.serverAddress, this.serverPort, this.restContext);
//		ResponseEntity<Suggestion> response = this.template.postForEntity(apiLink, request, Suggestion.class);
//		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
//		return response.getBody();
//	}
//	
//	public String updateSuggestion(String newSuggestionContent, boolean isCorrect, int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		Suggestion suggestion = this.getSuggestionById(id);
//		suggestion.setSuggestionContent(newSuggestionContent);
//		suggestion.setCorrect(isCorrect);
//		HttpEntity<Suggestion> request = new HttpEntity<Suggestion>(suggestion);
//		String apiLink = String.format("%s:%d/%s/Suggestions/%d", this.serverAddress, this.serverPort, this.restContext, id);
//		this.template.put(apiLink, request, Suggestion.class);
//		return apiLink;
//	}
//	
//	public String deleteSuggestion(int id) {
//		String apiLink = String.format("%s:%d/%s/Suggestions/%d", this.serverAddress, this.serverPort, this.restContext, id);
//		this.template.delete(apiLink);
//		return apiLink;
//	}
//}
