package de.hsog.models;

import de.hsog.compositekeys.QuestionHasSuggestionKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class QuestionHasSuggestion {

	@EmbeddedId
	private QuestionHasSuggestionKey id;

	/*
	 * TODO causing redundancy in DB (id_question(PK, FK), id_suggestion(PK, FK),
	 * is_correct, question_id(NN), suggestion_id(NN))
	 */
	@ManyToOne
	@MapsId("questionId")
	@JoinColumn(name = "questionId")
	private Question questionId;

	@ManyToOne
	@MapsId("suggestionId")
	@JoinColumn(name = "suggestionId")
	private Suggestion suggestionId;

	@Column(name = "isCorrect", nullable = false)
	private boolean isCorrect;
	
	public QuestionHasSuggestion(Question question, Suggestion suggestion, boolean isCorrect) {
		this.questionId = question;
		this.suggestionId = suggestion;
		this.isCorrect = isCorrect;
	}

	public QuestionHasSuggestionKey getId() {
		return id;
	}

	public void setId(QuestionHasSuggestionKey id) {
		this.id = id;
	}

	public Question getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Question questionId) {
		this.questionId = questionId;
	}

	public Suggestion getSuggestionId() {
		return suggestionId;
	}

	public void setSuggestionId(Suggestion suggestionId) {
		this.suggestionId = suggestionId;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
