package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hsog.dto.Category;
import de.hsog.errors.BackendServerEntityNotFoundException;
import de.hsog.errors.BackendServerInternalErrorException;
import de.hsog.restrepos.CategoryRESTRepo;

@Controller
@RequestMapping("/")
public class CategoryRouter {

	private CategoryRESTRepo categoryRepo;
	
	public CategoryRouter() {
		/*TODO: change address*/
		this.categoryRepo = new CategoryRESTRepo();
	}

	
	@GetMapping("/Categories")
	public String getForm(Model model) {
		model.addAttribute("category", new Category());
		return "html/categoryWizard";
	}
	
	@PostMapping("/Categories")
	public String submitForm(@ModelAttribute Category category) {
		try {
			this.categoryRepo.addCategory(category.getCategoryName());
		} catch (BackendServerEntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BackendServerInternalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/Categories";
	}
	
}
