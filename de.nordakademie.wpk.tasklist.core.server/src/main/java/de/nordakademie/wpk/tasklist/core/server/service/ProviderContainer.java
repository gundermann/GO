package de.nordakademie.wpk.tasklist.core.server.service;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.server.service.google.GoogleProvider;

public class ProviderContainer {

	private static ProviderContainer _instance;
	
	public static ProviderContainer getInstance(){
		if(_instance == null){
			_instance = new ProviderContainer();
		}
		return _instance;
	}

	public ProviderService getProvider(ProviderSetting setting) throws ProviderNotImplementedException {
		switch (setting.getProvider()) {
		case GOOGLE:
			return new GoogleProvider(setting);
//		case WUNDERLIST:
//			return new WunderlistProvider(setting);
		default:
			throw new ProviderNotImplementedException(setting.getProvider());
		}
	}

}
