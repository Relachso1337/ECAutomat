package de.hsog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="questionType",
		discriminatorType = DiscriminatorType.STRING)
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idQuestion")
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "varchar(999) default 'N/A'", unique = true)
	private String content;
	
	@Column(nullable = false)
	private Integer points;
	
	@ManyToOne
	@JoinColumn(name="category", referencedColumnName="idCategory")
	private Category category;
	
	@ManyToMany(mappedBy = "questions")
	List<Quiz> quizes = new ArrayList<>();
	
	public Question() {
		this.content = "N/A";
		this.points = 0;
	}
	
	public Question(String content, Integer points, Category category) {
		this.content = content;
		this.points = points;
		this.category = category;
	}
	
	public Question(String content, Integer points, Category fkCategory, List<Quiz> quizes) {
		this.content = content;
		this.points = points;
		this.category = fkCategory;
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

	public List<Integer> getQuizesIds() {
		List<Integer> helper = new ArrayList<>();
		for (Quiz q : this.quizes) {
			helper.add(q.getId());
		}
		return helper;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}
	
	public int getCategoryId() {
		return category.getId();
	}

	public String getCategoryName() {
		return category.getCategoryName();
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	public QuestionType getQuestiontype() {
//		return questiontype;
//	}
//
//	public void setQuestiontype(QuestionType questiontype) {
//		this.questiontype = questiontype;
//	}
	
	
}
