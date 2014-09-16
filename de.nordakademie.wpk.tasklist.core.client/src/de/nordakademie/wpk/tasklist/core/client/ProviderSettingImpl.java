package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class ProviderSettingImpl implements ProviderSetting{

	private String username;
	private String password;
	private Provider provider;

	public ProviderSettingImpl(Provider provider, String username, String password) {
		this.provider = provider;
		this.username = username;
		this.password = password;
	}
	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public Provider getProvider() {
		return provider;
	}

}
