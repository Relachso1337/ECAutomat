package de.hsog.dto;

public class MensaSpeise {

	private int id;
	private String essen;
	
	public MensaSpeise() {
		this.id = 0;
		this.essen = "Kartoffeln";
	}
	
	public MensaSpeise(int id, String essen) {
		this.id = id;
		this.essen = essen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEssen() {
		return essen;
	}

	public void setEssen(String essen) {
		this.essen = essen;
	}
	
	public String toString() {
		return String.format("ID: %d | essen: %s", this.id, this.essen);
	}
	
}
