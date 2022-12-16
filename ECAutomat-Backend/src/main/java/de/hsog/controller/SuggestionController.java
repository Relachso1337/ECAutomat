package de.hsog.controller;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import de.hsog.models.Question;
import de.hsog.models.QuestionHasSuggestion;
import de.hsog.models.Suggestion;
import de.hsog.repositories.QuestionRepository;
import de.hsog.repositories.SuggestionRepository;

@Controller
public class SuggestionController {
	
	private final SuggestionRepository suggestionRepository;
	private final QuestionRepository questionRepository;
	
	public SuggestionController(SuggestionRepository suggestionRepo, QuestionRepository questionRepo) {
		this.suggestionRepository = suggestionRepo;
		this.questionRepository = questionRepo;
	}
	
	@QueryMapping
	public Iterable<Suggestion> suggestions() {
		return this.suggestionRepository.findAll();
	}
	
	@QueryMapping
	public Optional<Suggestion> suggestionById(Integer id) {
		return this.suggestionRepository.findById(id);
	}
	
	/*TODO: maybe add BLOB to schema*/
	record SuggestionInput(String suggestionContent) {};
		
	@MutationMapping
	public Suggestion addSuggestion(@Argument SuggestionInput suggestion) {
		// TODO: test add
		Suggestion s = new Suggestion(suggestion.suggestionContent());
		return this.suggestionRepository.save(s);
	}
	
	@MutationMapping
	public Suggestion updateSuggestion(@Argument Integer id, @Argument Suggestion newSuggestion) {
		// TODO: test update
		Suggestion suggestion = this.suggestionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		suggestion.setSuggestionContent(newSuggestion.getSuggestionContent());
		return this.suggestionRepository.save(suggestion);
	}
	
	@MutationMapping
	public boolean deleteSuggestionById(@Argument Integer id) {
		// TODO: test delete
		try {
			this.suggestionRepository.deleteById(id);
		}  catch (Exception e) {
			return false;
		}
		return true;
	}
}
