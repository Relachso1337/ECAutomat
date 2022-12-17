package de.hsog.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

	
	private Integer id;

	private LocalDateTime playdate;
	
	private Integer maxScore;
	
	private String name;

	private Player player;
	
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

	public LocalDateTime getPlaydate() {
		return playdate;
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

	public Player getPlayer() {
		return player;
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
