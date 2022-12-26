package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/mensa")
public class MensaRouter {

	
	@GetMapping(value = "")
	public ModelAndView show_mensa() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/mensa.html");
		return modelAndView;
	}
	
	@GetMapping(value="/*")
	public ModelAndView unknownURL() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/mensa");
		return modelAndView;
	}
}
