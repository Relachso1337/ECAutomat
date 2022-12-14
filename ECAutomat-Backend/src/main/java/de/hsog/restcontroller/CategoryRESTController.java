package de.hsog.restcontroller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import de.hsog.models.Category;
import de.hsog.repositories.CategoryRepository;

@RestController
@RequestMapping(value = "/REST")
public class CategoryRESTController{

	private final CategoryRepository categoryRepository;
	private final ObjectMapper mapper;
	
	
	/**
	 * CRUD based RESTController
	 * @param categoryRepo
	 */
	public CategoryRESTController(CategoryRepository categoryRepo) {
		this.categoryRepository = categoryRepo;
		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@GetMapping(value="Categories", produces = "application/json")
	public ResponseEntity<String> categories() {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.categoryRepository.findAll());
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(responseBody, responseStatus);
	}
	
	@GetMapping(value="Categories/{id}", produces = "application/json")
	public ResponseEntity<String> categoryById(@PathVariable int id) {
		String responseBody = "";
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Category foundCategory = this.categoryRepository.findById(id).get();
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundCategory);
		} catch (JsonProcessingException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}  catch (NoSuchElementException e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}
	
	@PostMapping(value="Categories", consumes="application/json", produces = "application/json")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			category.setId(null);
			Category savedObj = this.categoryRepository.save(category);
			responseBody = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedObj);
		} catch (Exception e) {
			responseBody = e.toString();
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(responseBody, responseStatus);
	}

	@PutMapping(value="Categories/{id}", consumes="application/json", produces = "application/json")
	public ResponseEntity<String> updateCategory(@RequestBody Category categoryInput, @PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Category categoryDB = this.categoryRepository.findById(id).get();
			categoryDB.setCategoryName(categoryInput.getCategoryName());
			Category savedObj = this.categoryRepository.save(categoryDB);
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
	
	@DeleteMapping("Categories/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable int id) {
		String responseBody;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.categoryRepository.deleteById(id);
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
