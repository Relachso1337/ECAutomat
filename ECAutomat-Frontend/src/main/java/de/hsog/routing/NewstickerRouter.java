package de.hsog.routing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.hsog.utils.NewsArticle;
import de.hsog.utils.NewsWebscraper;

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
		NewsWebscraper scraper = new NewsWebscraper();
		ArrayList<String> linkList = new ArrayList<>();
		linkList.addAll(scraper.scrapeLinks());
		if (counter > linkList.size())
			counter = 0;
		String link = linkList.get(counter % linkList.size());
		NewsArticle article = new NewsArticle();
		article.setAuthor(scraper.getNewsAuthor(link));
		article.setImageDir(scraper.getNewsImage(link));
		article.setDate(scraper.getNewsDate(link));
		article.setHeadline(scraper.getNewsHeadline(link));
		article.setUrl("http://hs-offenburg.de" + link);
		article.setParagraphs(scraper.getNewsContent(link));
		try {
			outputStream = new FileOutputStream("./src/main/resources/static/media/news/news.json");
			outputStream.write(this.mapper.writeValueAsBytes(article));
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