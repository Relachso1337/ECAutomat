package de.hsog.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Quiz {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idQuiz")
	private Integer id;

	/* TODO maybe change playdate format, to eliminate errors with GraphQL and Database (Datetime) */
	@Column(nullable = false)
	private LocalDateTime playdate;
	
	/* TODO add function to DB-Scheme to add Score from seperate Questions */
	@Column(nullable = false)
	private Integer maxScore;
	
	@Column(nullable = true)
	private String name;

	@ManyToOne
	@JoinColumn(name="fkPlayer", referencedColumnName="idPlayer")
	private Player fkPlayer;
	
	@ManyToMany
	@JoinTable(
			name="quiz_has_question",
			joinColumns = @JoinColumn(name="idQuiz"),
			inverseJoinColumns = @JoinColumn(name="idQuestion")
			)
	private List<Question> questions;

	public Quiz() {
		this.name = "Quiz";
		this.playdate = LocalDateTime.now();
		this.maxScore = 0;
		this.questions = new ArrayList<>();
	}
	
	public Quiz(LocalDateTime playdate, Integer maxScore, Player user) {
		this.playdate = playdate;
		this.maxScore = maxScore;
		this.fkPlayer = user;		
	}
	
	public Quiz(String name, LocalDateTime playdate, Integer maxScore, Player user, List<Question> questions) {
		this.name = name;
		this.playdate = playdate;
		this.maxScore = maxScore;
		this.fkPlayer = user;
		this.questions = questions;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getPlaydate() {
		return playdate;
	}

	public void setPlaydate(LocalDateTime playdate) {
		this.playdate = playdate;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getFkUser() {
		return fkPlayer;
	}

	public void setFKPlayer(Player fkPlayer) {
		this.fkPlayer = fkPlayer;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
