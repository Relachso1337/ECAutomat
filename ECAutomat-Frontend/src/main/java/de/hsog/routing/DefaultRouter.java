package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultRouter{
	
	@GetMapping(value = "")
	public String index() {
		return "html/vorlesungsplan";
	}
}
