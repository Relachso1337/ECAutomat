package de.hsog.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hsog.models.SimpleQuestion;

public interface SimpleQuestionRepository extends CrudRepository<SimpleQuestion, Integer> {

}
