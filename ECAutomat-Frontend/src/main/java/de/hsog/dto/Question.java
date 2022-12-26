package de.hsog.dto;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private Integer id;

	private String content;

	private Integer points;

	private Category category;

	private List<Quiz> quizes;

	public Question() {
		this.content = "N/A";
		this.points = 0;
		this.quizes = new ArrayList<>();
	}

	public Question(String content, Integer points, Category fkCategory) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
	}

	public Question(String content, Integer points, Category fkCategory,
			List<Quiz> quizes) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
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

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}
	
}
