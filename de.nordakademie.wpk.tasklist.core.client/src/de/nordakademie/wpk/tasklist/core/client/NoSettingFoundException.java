package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.Provider;

/**
 * Wird geworfen, wenn keine Einstellungen gefunden wurden
 * 
 * @author Niels Gundermann
 *
 */
public class NoSettingFoundException extends Exception {

	private static final long serialVersionUID = 4L;
	private Provider provider;
	
	public NoSettingFoundException(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String getMessage() {
		return "Keine einstellung für diesen Provider gefunden: "+provider.toString();
	}

}
