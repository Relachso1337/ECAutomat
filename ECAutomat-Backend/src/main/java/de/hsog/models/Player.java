package de.hsog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPlayer")
	private Integer id;


	@Column(nullable = false)
	private String playerName;

	@Column(nullable = false, columnDefinition = "integer default 0")
	private Integer scoreQuizes;
	
	@OneToMany(mappedBy = "fkPlayer", cascade = CascadeType.ALL)
	private List<Quiz> quizes = new ArrayList<>();
	
	public Player() {
		this.playerName = "Hans Wurst";
		this.scoreQuizes = 0;
	}
	
	public Player(String playerName, Integer scoreQuizes) {
		this.playerName = playerName;
		this.scoreQuizes = scoreQuizes;
	}
	
	public Player(String playerName, Integer scoreQuizes, List<Quiz> quizes) {
		this.playerName = playerName;
		this.scoreQuizes = scoreQuizes;
		this.quizes = quizes;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getScoreQuizes() {
		return scoreQuizes;
	}

	public void setScoreQuizes(Integer scoreQuizes) {
		this.scoreQuizes = scoreQuizes;
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}
}
