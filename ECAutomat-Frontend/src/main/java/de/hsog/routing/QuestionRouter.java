package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsog.dto.Category;
import de.hsog.dto.Question;
import de.hsog.errors.BackendServerEntityNotFoundException;
import de.hsog.errors.BackendServerInternalErrorException;
import de.hsog.restrepos.CategoryRESTRepo;
import de.hsog.restrepos.QuestionRESTRepo;

@Controller
@RequestMapping("/")
public class QuestionRouter {

	private QuestionRESTRepo questionRepo;
	private CategoryRESTRepo categoryRepo;
	
	public QuestionRouter() {
		/*TODO: change address*/
		this.questionRepo = new QuestionRESTRepo();
		this.categoryRepo = new CategoryRESTRepo();
	}

	
	@GetMapping("/Questions")
	public String getForm(Model model) {
		model.addAttribute("question", new Question());
		try {
			model.addAttribute("categories", this.categoryRepo.getAllCategories());
		} catch (BackendServerEntityNotFoundException | BackendServerInternalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "html/questionWizard";
	}
	
	record QuestionInput(String content, int points, int categoryId) {};
	
	@PostMapping("/Questions")
	public String submitForm(@ModelAttribute QuestionInput q) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		System.out.println(q.content);
		System.out.println(q.points);
		Category category = this.categoryRepo.getCategoryById(q.categoryId);
		System.out.println(category.getCategoryName());
		this.questionRepo.addQuestion(q.content, q.points, category);
		return "redirect:/Questions";
	}
	
}
