package de.hsog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCategory")
	private Integer id;

	@Column(nullable = false, unique = true, columnDefinition = "varchar(255) default 'default'")
	private String categoryName;
	
	
	@OneToMany(mappedBy = "fkCategory", cascade = CascadeType.ALL)
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
