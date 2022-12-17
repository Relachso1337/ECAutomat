package de.hsog.routing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsog.dto.Category;
import de.hsog.restrepos.CategoryRESTRepo;
import de.hsog.dto.MensaMenu;
import de.hsog.graphqlrepos.CategoryGraphQLRepo;


@Controller
@RequestMapping("/")
public class DefaultRouter{
	
	@GetMapping(value = "")
	public String index() {
		return "html/index";
	}
	
	@GetMapping(value = "mensaplan")
	public String mensaplan(Model model) {	
		try {
			File file = ResourceUtils.getFile("classpath:static/media/misc/menu.json");
			ObjectMapper objectMapper = new ObjectMapper();
			MensaMenu menu = objectMapper.readValue(file, MensaMenu.class);
			model.addAttribute("speisen", menu.getMenu());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "html/mensaplan";
	}
	
	@GetMapping(value = "category")
	public String category(Model model) {
		CategoryRESTRepo repo = new CategoryRESTRepo();
		// Category cat = repo.getCategoryById(1);
		 model.addAttribute("repo", repo);
		// model.addAttribute("category", cat);
		// repo.addCategory("Finanzen");
		return "html/category";
	}
}
