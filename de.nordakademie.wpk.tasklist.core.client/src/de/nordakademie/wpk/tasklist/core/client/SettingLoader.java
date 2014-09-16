package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class SettingLoader {

	public ProviderSetting loadFromFile(String string) {
		String username = loadUsername();
		String password = loadPassword();
		Provider provider = loadProvider();
		return new ProviderSettingImpl(provider, username, password );
	}

	private Provider loadProvider() {
		return Provider.GOOGLE;
	}

	private String loadPassword() {
		return null;
	}

	private String loadUsername() {
		return "gundermann.niels.ng@googlemail.com";
	}

}
