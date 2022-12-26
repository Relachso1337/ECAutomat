package de.hsog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("MULTIPLECHOICE")
public class MultipleChoiceQuestion extends Question {

	
	@OneToMany(mappedBy = "question")
	List<Suggestion> suggestions = new ArrayList<>();
	
	public MultipleChoiceQuestion() {
		super();
		this.suggestions = new ArrayList<>();
	}
	
	public MultipleChoiceQuestion(String content, Integer points, Category category) {
		super(content, points, category);
		this.suggestions = new ArrayList<>();
	}

	public MultipleChoiceQuestion(String content, Integer points, Category category, List<Suggestion> suggestions) {
		super(content, points, category);
		this.suggestions = suggestions;
	}
	
	
	public MultipleChoiceQuestion(String content, Integer points, Category fkCategory, List<Quiz> quizes, List<Suggestion> suggestions) {
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
