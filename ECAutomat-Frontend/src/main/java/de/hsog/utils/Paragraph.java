package de.hsog.utils;


/**
 * Helperclass for Webscraper
 */
public class Paragraph {

	private String content;
	
	public Paragraph() {
		this.content = "No Content";
	}
	
	public Paragraph(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return this.content;
	}
}
