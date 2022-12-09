package de.hsog.compositekeys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class QuestionHasSuggestionKey implements Serializable {

	@Column(name="idQuestion")
	private Integer questionID;
	
	@Column(name="idSuggestion")
	private Integer suggestionID;

	public Integer getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	public Integer getSuggestionID() {
		return suggestionID;
	}

	public void setSuggestionID(Integer suggestionID) {
		this.suggestionID = suggestionID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(questionID, suggestionID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionHasSuggestionKey other = (QuestionHasSuggestionKey) obj;
		return Objects.equals(questionID, other.questionID) && Objects.equals(suggestionID, other.suggestionID);
	}	
}
