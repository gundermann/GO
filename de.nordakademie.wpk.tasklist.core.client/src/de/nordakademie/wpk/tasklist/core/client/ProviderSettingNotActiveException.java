package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.Provider;

public class ProviderSettingNotActiveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Provider provider;

	public ProviderSettingNotActiveException(Provider provider) {
		this.provider = provider;
	}

	@Override
	public String getMessage() {
		return "Für den Provider " + provider.toString()
				+ " wurden keine aktiven Einstellungen gefunden.";
	}

}
