package de.hsog.utils;

import java.util.ArrayList;
import java.util.List;

public class NewsArticle {

	private List<Paragraph> paragraphs;
	private String imageDir;
	private String headline;
	private String author;
	private String date;
	private String url;

	public NewsArticle() {
		this.paragraphs = new ArrayList<>();
		this.imageDir = "./media/news/1.jpg";
		this.headline = "Very cool Headline";
		this.author = "Mike Ockherz";
		this.date = "1 Januar 1999";
		this.url = "hs-offenburg.de";
	}
	
	public NewsArticle(String headline, String author, String date, String url, String imageDir) {
		this.paragraphs = new ArrayList<>();
		this.imageDir = imageDir;
		this.headline = headline;
		this.author = author;
		this.date = date;
		this.url = url;
	}
	
	public NewsArticle(List<Paragraph> paragraphs, String imageDir, String headline, String author, String date, String url) {
		this.paragraphs = paragraphs;
		this.imageDir = imageDir;
		this.headline = headline;
		this.author = author;
		this.date = date;
		this.url = url;
	}
	
	
	
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	public String getHeadline() {
		return headline;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	} 
}
