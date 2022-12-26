package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/quiz")
public class QuizRouter {

	@GetMapping(value="")
	public ModelAndView show_quiz() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/quiz.html");
		return modelAndView;
	}
	
	@GetMapping(value="/*")
	public ModelAndView unknownURL() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/quiz");
		return modelAndView;
	}
}
