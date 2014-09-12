package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.todolist.core.api.ProviderSetting;

public class ProviderSettingImpl implements ProviderSetting{

	private String username;
	private String password;

	public ProviderSettingImpl(String username, String password) {
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

}
