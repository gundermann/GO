package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.Provider;

/**
 * Wird geworfen, wenn eine Einstellung nicht aktiv ist
 * 
 * @author Niels Gundermann
 *
 */
public class ProviderSettingNotActiveException extends Exception {

	private static final long serialVersionUID = 1L;

	private Provider provider;

	public ProviderSettingNotActiveException(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String getMessage() {
		return "F�r den Provider " + provider.toString()
				+ " wurden keine aktiven Einstellungen gefunden.";
	}

}
