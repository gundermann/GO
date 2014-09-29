package de.nordakademie.wpk.tasklist.core.server.service;

import de.nordakademie.wpk.tasklist.core.api.Provider;

public class ProviderNotImplementedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public ProviderNotImplementedException(Provider provider) {
		message = "Der Service des Providers " + provider.name()
				+ " ist nicht implementiert.";
	}

}
