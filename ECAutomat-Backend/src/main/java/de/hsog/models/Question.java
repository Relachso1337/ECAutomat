package de.hsog.models;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idQuestion")
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "varchar(999) default 'N/A'")
	private String content;
	
	@Column(nullable = false)
	private Integer points;
	
	@ManyToOne
	@JoinColumn(name="category", referencedColumnName="idCategory")
	private Category category;
	
	@OneToMany(mappedBy = "question")
	List<Suggestion> suggestions = new ArrayList<>();
	
	@ManyToMany(mappedBy = "questions")
	List<Quiz> quizes = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	QuestionType questiontype;
	
	/*TODO Maybe obsolete because of implementation issues */
	@Column(nullable = true)
	private Blob questionImage;
	
	public Question() {
		this.content = "N/A";
		this.points = 0;
		this.questiontype = QuestionType.SIMPLE;
	}
	
	public Question(String content, Integer points, Category fkCategory, QuestionType qType) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
		this.questiontype = qType;  // TODO: Fix me
	}
	
	public Question(String content, Integer points, Category fkCategory, List<Suggestion> suggestions, List<Quiz> quizes, QuestionType qType) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
		this.suggestions = suggestions;
		this.questiontype = qType;  // TODO: Fix me
		this.quizes = quizes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}

	public Blob getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(Blob questionImage) {
		this.questionImage = questionImage;
	}	
}
