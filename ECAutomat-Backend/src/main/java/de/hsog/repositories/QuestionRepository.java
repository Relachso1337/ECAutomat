package de.hsog.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hsog.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
