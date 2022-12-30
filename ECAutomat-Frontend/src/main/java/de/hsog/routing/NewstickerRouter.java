package de.hsog.routing;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsog.utils.Webscraper;

@RestController
@RequestMapping("/news")
public class NewstickerRouter {

	private ObjectMapper mapper;
	private int counter;

	public NewstickerRouter() {
		this.mapper = new ObjectMapper();
		this.counter = 0;
	}

	@GetMapping(value = "")
	public ModelAndView show_quiz() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("html/newsticker.html");
		return modelAndView;
	}

	@GetMapping(value = "/scrapeNews", produces = "appplication/json")
	public String scrapenews() throws IOException {
		FileOutputStream outputStream = null;
		Webscraper scraper = new Webscraper();
		ArrayList<String> linkList = new ArrayList<>();
		linkList.addAll(scraper.scrapeLinks());
		if (counter > linkList.size())
			counter = 0;
		String link = linkList.get(counter % linkList.size());
		scraper.getNewsImage(link);
		try {
			outputStream = new FileOutputStream("./src/main/resources/static/media/news/news.json");
			outputStream.write(this.mapper.writeValueAsBytes(scraper.getNewsContent(link)));
		} catch (Exception e) {
			return e.toString();
		} finally {
			outputStream.close();
		}
		this.counter++;
		return "Nice";
	}

	@GetMapping(value = "/getNewsFile")
	public String getnewsJSON() {
		return "";
	}

	@GetMapping(value = "/*")
	public ModelAndView unknownURL() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/news");
		return modelAndView;
	}
}