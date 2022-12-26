package de.hsog.dto;

import java.util.List;

public class SimpleQuestion extends Question {

	private boolean isCorrect;
	
	public SimpleQuestion() {
		super();
		this.isCorrect = false;
	}
	
	public SimpleQuestion(String content, Integer points, Category fkCategory, boolean isCorrect) {
		super(content, points, fkCategory);
		this.isCorrect = isCorrect;
	}
	
	public SimpleQuestion(String content, Integer points, Category fkCategory,
			List<Quiz> quizes, boolean isCorrect) {
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
