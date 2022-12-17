package de.hsog.errors;

import org.springframework.http.HttpStatusCode;

public class ErrorHandler {

	public void checkForHTMLError(HttpStatusCode status, String responseBody) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
		if (status.is4xxClientError()) {
			throw new BackendServerEntityNotFoundException();
		}  else if (status.is5xxServerError()) {
			throw new BackendServerInternalErrorException(responseBody);
		}
	}

}
