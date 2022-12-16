package de.hsog.dto;

import java.util.ArrayList;
import java.util.List;

public class Category {
	
	private Integer id;

	private String categoryName;
	
	private List<Question> questions = new ArrayList<>();
	
	public Category() {
		this.categoryName = "default";
	}
	
	public Category(String categoryName, List<Question> questions) {
		this.categoryName = categoryName;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}
