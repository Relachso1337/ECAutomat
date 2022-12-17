package de.hsog.restrepos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.hsog.dto.Category;
import de.hsog.errors.BackendServerEntityNotFoundException;
import de.hsog.errors.BackendServerInternalErrorException;
import de.hsog.errors.ErrorHandler;

public class CategoryRESTRepo {
	
	private final String serverAddress;
	private final int serverPort;
	private final String restContext;
	private final RestTemplate template;
	private ErrorHandler handler;
	
	public CategoryRESTRepo() {
		this.serverAddress = "http://127.0.0.1";
		this.serverPort = 8888;
		this.restContext = "api/v1/REST";
		this.template = new RestTemplate();
		this.handler = new ErrorHandler();
	}
	
	public CategoryRESTRepo(String address, int port, String restContext) {
		this.serverAddress = address;
		this.serverPort = port;
		this.restContext = restContext;
		this.template = new RestTemplate();
	} 
	
	public List<Category> getAllCategories() throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		ResponseEntity<Category[]> response;
		String apiLink = String.format("%s:%d/%s/Categories", this.serverAddress, this.serverPort, this.restContext); 
		response = this.template.getForEntity(apiLink, Category[].class);
		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
		return Arrays.asList(response.getBody());
	}
	
	public Category getCategoryById(int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		String apiLink = String.format("%s:%d/%s/Categories/%d", this.serverAddress, this.serverPort, this.restContext, id); 
		ResponseEntity<Category> response = this.template.getForEntity(apiLink, Category.class);
		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
		return response.getBody();
	}
	
	public Category addCategory(String categoryName) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		Category category = new Category(categoryName, new ArrayList<>());
		HttpEntity<Category> request = new HttpEntity<Category>(category);
		String apiLink = String.format("%s:%d/%s/Categories", this.serverAddress, this.serverPort, this.restContext);
		ResponseEntity<Category> response = this.template.postForEntity(apiLink, request, Category.class);
		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
		return response.getBody();
	}
	
	public String updateCategory(String newCategoryName, int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		Category category = this.getCategoryById(id);
		category.setCategoryName(newCategoryName);
		HttpEntity<Category> request = new HttpEntity<Category>(category);
		String apiLink = String.format("%s:%d/%s/Categories/%d", this.serverAddress, this.serverPort, this.restContext, id);
		this.template.put(apiLink, request, Category.class);
		return apiLink;
	}
	
	public String deleteCategory(int id) {
		String apiLink = String.format("%s:%d/%s/Categories/%d", this.serverAddress, this.serverPort, this.restContext, id);
		this.template.delete(apiLink);
		return apiLink;
	}
}
