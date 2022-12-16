package de.hsog.graphqlcontroller;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import de.hsog.models.Player;
import de.hsog.repositories.PlayerRepository;

@Controller
public class PlayerController {

	private final PlayerRepository playerRepository;

	public PlayerController(PlayerRepository playerRepo) {
		this.playerRepository = playerRepo;
	}

	@QueryMapping
	public Iterable<Player> players() {
		return this.playerRepository.findAll();
	}

	@QueryMapping
	public Optional<Player> playerById(@Argument Integer id) {
		return this.playerRepository.findById(id);
	}

	record PlayerInput(String playerName, Integer scoreQuizes) {};
	
	@MutationMapping
	public Player addPlayer(@Argument PlayerInput player) {
		Player u = new Player(player.playerName(), player.scoreQuizes());
		return this.playerRepository.save(u);
	}
	
	@MutationMapping
	public Player updatePlayer(@Argument Integer id, @Argument Player newPlayer) {
		Player player = this.playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		player.setPlayerName(newPlayer.getPlayerName());
		player.setScoreQuizes(newPlayer.getScoreQuizes());
		return this.playerRepository.save(player);
	}

	@MutationMapping
	public boolean deletePlayerById(@Argument Integer id) {
		try {
			this.playerRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
