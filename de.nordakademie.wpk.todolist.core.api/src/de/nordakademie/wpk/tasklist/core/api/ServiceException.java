package de.nordakademie.wpk.tasklist.core.api;

/**
 * Wird geworfen, wenn eine Exception auf dem Server auftritt
 * 
 * @author Niels Gundermann
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public ServiceException(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return message;
	}
}
