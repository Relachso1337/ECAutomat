package de.hsog.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hsog.models.MultipleChoiceQuestion;

public interface MultipleChoiceQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer> {

}
