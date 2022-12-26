package de.hsog.models;

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
	private MultipleChoiceQuestion question;
	
	@Column(nullable=false)
	private boolean isCorrect;
	
	public Suggestion() {
		this.suggestionContent = "N/A";
		this.isCorrect = false;
	}
	
	public Suggestion(String suggestionContent, boolean isCorrect) {
		this.suggestionContent = suggestionContent;
		this.isCorrect = isCorrect;
	}
	
	public Suggestion(String suggestionContent, MultipleChoiceQuestion quest, boolean isCorrect) {
		this.suggestionContent = suggestionContent;
		this.question = quest;
		this.isCorrect = isCorrect;
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

	public Integer getQuestionId() {
		return question.getId();
	}
	
	public String getQuestionContent() {
		return question.getContent();
	}

	public void setQuestions(MultipleChoiceQuestion question) {
		this.question = question;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
}
