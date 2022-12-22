package de.hsog.dto;

public class Suggestion {

	private Integer id;
	
	private String suggestionContent;
	
	private Question question;
	
	private boolean isCorrect;
	
	public Suggestion() {
		this.suggestionContent = "N/A";
		this.isCorrect = false;
	}
	
	public Suggestion(String suggestionContent) {
		this.suggestionContent = suggestionContent;
	}
	
	public Suggestion(String suggestionContent, Question quest, boolean isCorrect) {
		this.suggestionContent = suggestionContent;
		this.question = quest;
		this.isCorrect = isCorrect;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestions(Question question) {
		this.question = question;
	}
	
	
}
