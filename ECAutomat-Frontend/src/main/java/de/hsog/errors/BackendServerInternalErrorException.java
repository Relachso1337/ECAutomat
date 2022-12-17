package de.hsog.errors;

@SuppressWarnings("serial")
public class BackendServerInternalErrorException extends Exception{

	public BackendServerInternalErrorException(String errorMsg) {
		super("The Backend Server encountered an fatal internal Error: " + errorMsg + " STATUS CODE: 500");
	}
}
