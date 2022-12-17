package de.hsog.graphqlcontroller;

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
	
	public SuggestionController(SuggestionRepository suggestionRepo) {
		this.suggestionRepository = suggestionRepo;
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
	record SuggestionInput(String suggestionContent, boolean isCorrect) {};
		
	@MutationMapping
	public Suggestion addSuggestion(@Argument SuggestionInput suggestion) {
		Suggestion s = new Suggestion(suggestion.suggestionContent(), suggestion.isCorrect);
		return this.suggestionRepository.save(s);
	}
	
	@MutationMapping
	public Suggestion updateSuggestion(@Argument Integer id, @Argument Suggestion newSuggestion) {
		Suggestion suggestion = this.suggestionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		suggestion.setSuggestionContent(newSuggestion.getSuggestionContent());
		return this.suggestionRepository.save(suggestion);
	}
	
	@MutationMapping
	public boolean deleteSuggestionById(@Argument Integer id) {
		try {
			this.suggestionRepository.deleteById(id);
		}  catch (Exception e) {
			return false;
		}
		return true;
	}
}
