package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;

/**
 * Bildet Einstellungen ab, die vom Nutzer je Provider vorgenommen werden können
 * 
 * @author Niels Gundermann
 *
 */
public class ProviderSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Provider provider;
	private boolean active;

	public ProviderSetting() {
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Provider getProvider() {
		return provider;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
