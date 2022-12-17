package de.hsog.errors;

@SuppressWarnings("serial")
public class BackendServerEntityNotFoundException extends Exception{

	public BackendServerEntityNotFoundException() {
		super("The Backend Server did not find the requested Entity on given URL STATUS CODE: 404");
	}
}
