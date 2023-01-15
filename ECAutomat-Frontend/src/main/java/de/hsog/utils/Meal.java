package de.hsog.utils;

import java.util.ArrayList;
import java.util.List;

public class Meal {

	private String day;
	private List<Ingredient> ingredients;
	private boolean isVegetarian;
	private boolean isWishVegan;
	private boolean isVegan;
	private double studentPrice;
	private double employeePrice;
	private double guestPrice;
	
	public Meal() {
		this.day = "Tirdas 01. Sonnaufgang 4. Ära";
		this.ingredients = new ArrayList<>();
		this.isVegetarian = true;
		this.isWishVegan = false;
		this.isVegan = false;
		this.studentPrice = 3.9;
		this.employeePrice = 5.9;
		this.guestPrice = 8.0;
	}

	public Meal(String day, boolean isVegetarian, double sPrice, double ePrice, double gPrice) {
		this.day = day;
		this.ingredients = new ArrayList<>();
		this.isVegetarian = isVegetarian;
		this.isWishVegan = false;
		this.isVegan = false;
		this.studentPrice = sPrice;
		this.employeePrice = ePrice;
		this.guestPrice = gPrice;
	}

	public Meal(String day, List<Ingredient> ingredients, boolean isVegetarian, boolean isWishVegan, boolean isVegan, double sPrice, double ePrice, double gPrice) {
		this.day = day;
		this.ingredients = ingredients;
		this.isVegetarian = isVegetarian;
		this.isWishVegan = isWishVegan;
		this.isVegan = isVegan;
		this.studentPrice = sPrice;
		this.employeePrice = ePrice;
		this.guestPrice = gPrice;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public boolean isWishVegan() {
		return isWishVegan;
	}

	public void setWishVegan(boolean isWishVegan) {
		this.isWishVegan = isWishVegan;
	}

	public boolean isVegan() {
		return isVegan;
	}

	public void setVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}

	public double getStudentPrice() {
		return studentPrice;
	}

	public void setStudentPrice(double studentPrice) {
		this.studentPrice = studentPrice;
	}

	public double getEmployeePrice() {
		return employeePrice;
	}

	public void setEmployeePrice(double employeePrice) {
		this.employeePrice = employeePrice;
	}

	public double getGuestPrice() {
		return guestPrice;
	}

	public void setGuestPrice(double guestPrice) {
		this.guestPrice = guestPrice;
	}
}
