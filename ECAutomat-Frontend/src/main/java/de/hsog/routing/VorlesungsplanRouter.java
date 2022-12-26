package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/vorlesungsplan")
public class VorlesungsplanRouter {

	@GetMapping(value="")
	public ModelAndView show_vorlesungsplan() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/vorlesungsplan.html");
		return modelAndView;
	}
	
	@GetMapping(value="/*")
	public ModelAndView unknownURL() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/vorlesungsplan");
		return modelAndView;
	}
}
