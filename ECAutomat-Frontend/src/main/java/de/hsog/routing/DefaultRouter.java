package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class DefaultRouter{
	
	@GetMapping(value = "")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/startseite.html");
		return modelAndView;
	}
	
	@GetMapping(value = "video")
	public ModelAndView video() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/video.html");
		return modelAndView;
	}
	
	@GetMapping(value="/*")
	public ModelAndView unknownURL() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
}
