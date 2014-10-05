package de.nordakademie.wpk.tasklist.core.api;

/**
 * Standardeinstellung für Google
 * @author Niels Gundermann
 *
 */
public class GoogleSetting extends ProviderSetting{

	private static final long serialVersionUID = 1L;

	@Override
	public Provider getProvider() {
		return Provider.GOOGLE;
	}

	@Override
	public String getUsername() {
		return System.getProperty("user.name");
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
