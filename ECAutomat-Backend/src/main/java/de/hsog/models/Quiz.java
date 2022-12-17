package de.hsog.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	@Column(nullable = false)
	private Integer maxScore;
	
	@Column(nullable = true)
	private String name;

	@ManyToOne
	@JoinColumn(name="player", referencedColumnName="idPlayer")
	private Player player;
	
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
	
	public Quiz(String name, String playdate, Integer maxScore, Player user) {
		this.name = name;
		this.maxScore = maxScore;
		this.player = user;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.playdate = LocalDateTime.parse(playdate, formatter);
	}
	
	public Quiz(String name, String playdate, Integer maxScore, Player user, List<Question> questions) {
		this.name = name; 
		this.maxScore = maxScore;
		this.player = user;
		this.questions = questions;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public LocalDateTime getPlaydate() {
//		return playdate;
//	}
	
	public String getPlayDate() {
		return this.playdate.toString();
	}

	public void setPlaydate(LocalDateTime playdate) {
		this.playdate = playdate;
	}
	
	public void setStringPlayDate(String playdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.playdate = LocalDateTime.parse(playdate, formatter); 
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

	public String getPlayerName() {
		return player.getPlayerName();
	}
	
	public Integer getPlayerId() {
		return player.getId();
	}

	public void setPlayer(Player fkPlayer) {
		this.player = fkPlayer;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
