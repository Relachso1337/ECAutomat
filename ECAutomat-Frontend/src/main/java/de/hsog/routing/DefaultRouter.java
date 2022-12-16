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
import de.hsog.graphqlcontroller.CategoryGraphQLController;
import de.hsog.mensaplan.MensaMenu;


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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "html/mensaplan";
	}
	
	@GetMapping(value = "category")
	public String category(Model model) {
		CategoryGraphQLController controller = new CategoryGraphQLController();
		Category cat = controller.getCategoryById(1);
		model.addAttribute("category", cat);
		return "html/category";
	}
}
