package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class ProviderSettingImpl implements ProviderSetting{

	private String username;
	private String password;
	private Provider provider;
	
	public ProviderSettingImpl(){
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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
