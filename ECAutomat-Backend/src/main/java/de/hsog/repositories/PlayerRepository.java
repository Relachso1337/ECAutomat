package de.hsog.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hsog.models.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
