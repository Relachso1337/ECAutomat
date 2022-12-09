package de.hsog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSuggestion")
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "varchar(999) default 'N/A'")
	private String suggestionContent;
	
	@OneToMany(mappedBy = "suggestionId")
	List<QuestionHasSuggestion> questions = new ArrayList<>();
	
	public Suggestion(String suggestionContent) {
		this.suggestionContent = suggestionContent;
	}
	
	public Suggestion(String suggestionContent, List<QuestionHasSuggestion> questions) {
		this.suggestionContent = suggestionContent;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuggestionContent() {
		return suggestionContent;
	}

	public void setSuggestionContent(String suggestionContent) {
		this.suggestionContent = suggestionContent;
	}

	public List<QuestionHasSuggestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionHasSuggestion> questions) {
		this.questions = questions;
	}
	
	
}
