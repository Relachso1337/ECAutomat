package de.hsog.dto;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
	
	private List<Suggestion> suggestions;
	
	public MultipleChoiceQuestion() {
		super();
		this.suggestions = new ArrayList<>();
	}
	
	public MultipleChoiceQuestion(String content, Integer points, Category fkCategory) {
		super(content, points, fkCategory);
		this.suggestions = new ArrayList<>();
	}
	
	public MultipleChoiceQuestion(String content, Integer points, Category fkCategory,
			List<Quiz> quizes, List<Suggestion> suggestions) {
		super(content, points, fkCategory, quizes);
		this.suggestions = suggestions;
	}
	
	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
}
