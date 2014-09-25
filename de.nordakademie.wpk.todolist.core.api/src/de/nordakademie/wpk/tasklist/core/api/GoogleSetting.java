package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;

public class GoogleSetting implements ProviderSetting, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Provider getProvider() {
		return Provider.GOOGLE;
	}

	@Override
	public String getUsername() {
		return "gundermann.niels.ng@googlemail.com";
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
