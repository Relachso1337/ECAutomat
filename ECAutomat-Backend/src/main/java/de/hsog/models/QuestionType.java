package de.hsog.models;

public enum QuestionType {
	
	SIMPLE, MULTIPLECHOICE;

	public static QuestionType enumOf(String name) {
		if(name == null) {
			return QuestionType.SIMPLE;
		}
		switch (name.toUpperCase()) {
		case ("MULTIPLECHOICE"): {
			return QuestionType.MULTIPLECHOICE;
		}
		default:
			return QuestionType.SIMPLE;
		}
	}
}
