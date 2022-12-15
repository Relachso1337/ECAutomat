package de.hsog.dto;

public class Suggestion {

	private Integer id;
	
	private String suggestionContent;
	
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
