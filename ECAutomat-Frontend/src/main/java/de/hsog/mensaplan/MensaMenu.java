package de.hsog.mensaplan;

import java.util.ArrayList;
import java.util.List;


public class MensaMenu {

	private List<Mensaspeise> menu;
	
	public MensaMenu() {
		this.menu = new ArrayList<>();
	}
	
	public MensaMenu(List<Mensaspeise> speisen) {
		this.menu = speisen;
	}

	public List<Mensaspeise> getMenu() {
		return menu;
	}

	public void setMenu(List<Mensaspeise> menu) {
		this.menu = menu;
	}	
}
