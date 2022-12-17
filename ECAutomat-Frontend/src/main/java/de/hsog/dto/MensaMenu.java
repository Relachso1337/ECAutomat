package de.hsog.dto;

import java.util.ArrayList;
import java.util.List;


public class MensaMenu {

	private List<MensaSpeise> menu;
	
	public MensaMenu() {
		this.menu = new ArrayList<>();
	}
	
	public MensaMenu(List<MensaSpeise> speisen) {
		this.menu = speisen;
	}

	public List<MensaSpeise> getMenu() {
		return menu;
	}

	public void setMenu(List<MensaSpeise> menu) {
		this.menu = menu;
	}	
}
