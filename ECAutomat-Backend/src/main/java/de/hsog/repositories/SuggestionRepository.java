package de.hsog.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hsog.models.Suggestion;

public interface SuggestionRepository extends CrudRepository<Suggestion, Integer> {

}
