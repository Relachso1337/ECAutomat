package de.hsog.dto;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class Question {
	
	private Integer id;
	
	private String content;
	
	private Integer points;
	
	private Category category;
	
	List<Suggestion> suggestions = new ArrayList<>();
	
	List<Quiz> quizes = new ArrayList<>();
	
	QuestionType questiontype;
	
	/*TODO Maybe obsolete because of implementation issues */
	private Blob questionImage;
	
	public Question() {
		this.content = "N/A";
		this.points = 0;
		this.questiontype = QuestionType.SIMPLE;
	}
	
	public Question(String content, Integer points, Category fkCategory, QuestionType qType) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
		this.questiontype = qType;
	}
	
	public Question(String content, Integer points, Category fkCategory, List<Suggestion> suggestions, List<Quiz> quizes, QuestionType qType) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
		this.suggestions = suggestions;
		this.questiontype = qType;
		this.quizes = quizes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPoints() {
		return points;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}

	public Blob getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(Blob questionImage) {
		this.questionImage = questionImage;
	}	
}
