package de.hsog.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SIMPLE")
public class SimpleQuestion extends Question {

	@Column(nullable = false, columnDefinition = "tinyint(1) default 0")
	private boolean isCorrect;
	
	
	public SimpleQuestion() {
		super();
		this.isCorrect = false;
	}

	public SimpleQuestion(String content, Integer points, Category category, boolean isCorrect) {
		super(content, points, category);
		this.isCorrect = isCorrect;
	}
	
	
	public SimpleQuestion(String content, Integer points, Category fkCategory, List<Quiz> quizes, boolean isCorrect) {
		super(content, points, fkCategory, quizes);
		this.isCorrect = isCorrect;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
