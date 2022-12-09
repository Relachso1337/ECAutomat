package de.hsog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import de.hsog.models.Category;
import de.hsog.models.Question;
import de.hsog.repositories.CategoryRepository;

@Controller
public class CategoryController {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@QueryMapping
	public Iterable<Category> categories() {
		return this.categoryRepository.findAll();
	}
	
	@QueryMapping
	public Optional<Category> categoryById(@Argument Integer id) {
		return this.categoryRepository.findById(id);
	}
	
	record CategoryInput(String categoryName, List<Question> quizes) {}; 
	
	@MutationMapping
	public Category addCategory(@Argument CategoryInput category) {
		Category c = new Category(category.categoryName(), category.quizes());
		return this.categoryRepository.save(c);
	}
	
	@MutationMapping
	public Category updateCategory(@Argument Integer id, @Argument CategoryInput newCategory) {
		Category category = this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		category.setCategoryName(newCategory.categoryName());
		return this.categoryRepository.save(category);
	}
	
	@MutationMapping
	public boolean deleteCategoryById(@Argument Integer id) {
		try {
			this.categoryRepository.deleteById(id);
		}  catch (Exception e) {
			return false;
		}
		return true;
	}
}
