package de.hsog.controller;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import de.hsog.models.Category;
import de.hsog.models.Question;
import de.hsog.repositories.CategoryRepository;
import de.hsog.repositories.QuestionRepository;

@Controller
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	private final CategoryRepository categoryRepository;
	
	public QuestionController(QuestionRepository questionRepo, CategoryRepository categoryRepo) {
		this.categoryRepository = categoryRepo;
		this.questionRepository = questionRepo;
	}
	
	@QueryMapping
	public Iterable<Question> questions() {
		return this.questionRepository.findAll();
	}
	
	@QueryMapping
	public Optional<Question> questionById(@Argument Integer id) {
		return this.questionRepository.findById(id);
	}

	/*TODO: do record + add content to schema.gql*/
	record QuestionInput(String content, Integer points, Integer categoryID) {};
	
	@MutationMapping
	public Question addQuestion(@Argument QuestionInput question) {
		// TODO: test add
		Category c = this.categoryRepository.findById(question.categoryID()).orElseThrow(() -> new IllegalArgumentException());
		Question q = new Question(question.content(), question.points(), c);
		return this.questionRepository.save(q);
	}
	
	@MutationMapping
	public Question updateQuestion(@Argument Integer id, @Argument QuestionInput newQuestion) {
		// TODO: test update
		Question question = this.questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		question.setContent(newQuestion.content());
		question.setPoints(newQuestion.points());
		return this.questionRepository.save(question);
	}
	
	@MutationMapping
	public boolean deleteQuestionById(@Argument Integer id) {
		// TODO: test delete
		try {
			this.questionRepository.deleteById(id);
		}  catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
