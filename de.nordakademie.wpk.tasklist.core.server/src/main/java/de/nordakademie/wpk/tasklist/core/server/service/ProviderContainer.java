package de.nordakademie.wpk.tasklist.core.server.service;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.server.service.google.GoogleProvider;
import de.nordakademie.wpk.tasklist.core.server.service.wunderlist.WunderlistProvider;

/**
 * Hier werden die einzelnen Provider auf dem Server angemeldet
 * 
 * @author Niels Gundermann
 *
 */
public class ProviderContainer {

	private static ProviderContainer _instance;

	public static ProviderContainer getInstance() {
		if (_instance == null) {
			_instance = new ProviderContainer();
		}
		return _instance;
	}

	public ProviderService getProvider(ProviderSetting setting)
			throws ProviderNotImplementedException, ServiceException {
		switch (setting.getProvider()) {
		case GOOGLE:
			return new GoogleProvider(setting);
		case WUNDERLIST:
			return new WunderlistProvider(setting);
		default:
			throw new ProviderNotImplementedException(setting.getProvider());
		}
	}

}
