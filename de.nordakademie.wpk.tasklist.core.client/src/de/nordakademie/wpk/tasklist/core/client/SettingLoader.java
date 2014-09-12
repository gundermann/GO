package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.todolist.core.api.ProviderSetting;

public class SettingLoader {

	public ProviderSetting loadFromFile(String string) {
		String username = loadUsername();
		String password = loadPassword();
		return new ProviderSettingImpl(username, password );
	}

	private String loadPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	private String loadUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
