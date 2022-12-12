package de.hsog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSuggestion")
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "varchar(999) default 'N/A'")
	private String suggestionContent;
	
	@ManyToOne
	@JoinColumn(name="question", referencedColumnName = "idQuestion")
	private Question question;
	
	public Suggestion() {
		this.suggestionContent = "N/A";
	}
	
	public Suggestion(String suggestionContent) {
		this.suggestionContent = suggestionContent;
	}
	
	public Suggestion(String suggestionContent, Question quest) {
		this.suggestionContent = suggestionContent;
		this.question = quest;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestions(Question question) {
		this.question = question;
	}
	
	
}
