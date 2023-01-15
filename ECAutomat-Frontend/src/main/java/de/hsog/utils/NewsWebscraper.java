package de.hsog.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class NewsWebscraper {

	/**
	 * Gets multiple available News Articles
	 * @return
	 */
	public Set<String> scrapeLinks() {
		WebClient client = new WebClient();
		Set<String> linklist = new HashSet<String>();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage("https://www.hs-offenburg.de/");
			for (HtmlAnchor n : page.getAnchors()) {
				if (n.getHrefAttribute().contains("/news-detailseite/article")) {
					linklist.add(n.getHrefAttribute());
				}
			}
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.close();
		}
		return linklist;
	}

	/**
	 * Gets the News Image from an article
	 * @return
	 */
	public String getNewsImage(String url) {
		WebClient client = new WebClient();
		String webLink = "https://www.hs-offenburg.de" + url;
		String saveDir = "./src/main/resources/static/media/news/1.jpg";
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage(webLink);
			HtmlImage image = page.<HtmlImage>getFirstByXPath("//img[@class='img-responsive']");
			image.saveAs(new File(saveDir));
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.close();
		}
		return saveDir;
	}
	
	public String getNewsHeadline(String url) {
		WebClient client = new WebClient();
		String webLink = "https://www.hs-offenburg.de" + url;
		String headline = "";
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage(webLink);
			HtmlElement el = page.getFirstByXPath("//h3[@itemprop='headline']");
			headline = el.getTextContent();
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.close();
		}
		return headline;
	}
	
	public String getNewsAuthor(String url) {
		WebClient client = new WebClient();
		String webLink = "https://www.hs-offenburg.de" + url;
		String author = "";
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage(webLink);
			HtmlElement el = page.getFirstByXPath("//span[@itemprop='author']//span[@itemprop='name']");
			author = el.getTextContent();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.close();
		}
		return author;
	}
	
	public String getNewsDate(String url) {
		WebClient client = new WebClient();
		String webLink = "https://www.hs-offenburg.de" + url;
		String date_ = "1 Januar 1999";
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage(webLink);
			HtmlElement el = page.getFirstByXPath("//time[@itemprop='datePublished']");
			date_ = el.getTextContent();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.close();
		}
		return date_;
	}
	
	/**
	 * Gets content from an Article
	 * @return
	 */
	public List<Paragraph> getNewsContent(String url) {
		WebClient client = new WebClient();
		String webLink = "https://www.hs-offenburg.de" + url;
		ArrayList<Paragraph> article = new ArrayList<Paragraph>();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage(webLink);
			List<HtmlElement> articleList = page.getByXPath("//div[@class='news-text-wrap']//p");
			for (HtmlElement par : articleList ) {
				String par_content = par.getTextContent();
				if (!par_content.equals("") && !par_content.equals(" ") && !par_content.equals("\t")) {
					article.add(new Paragraph(par_content));
				}
			}
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return article;
	}
}
