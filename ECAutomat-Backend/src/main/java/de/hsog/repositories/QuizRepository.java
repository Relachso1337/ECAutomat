package de.hsog.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hsog.models.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

}
