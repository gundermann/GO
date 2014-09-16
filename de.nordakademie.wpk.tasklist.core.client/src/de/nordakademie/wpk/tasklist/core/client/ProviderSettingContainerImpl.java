package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ProviderSettingContainer;

public class ProviderSettingContainerImpl implements ProviderSettingContainer{

	@Override
	public ProviderSetting getSettings(Provider provider) throws NoSettingFoundException {
		SettingLoader loader = new SettingLoader();
		switch (provider) {
		case GOOGLE:
			return loader.loadFromFile("file");
		default:
			 throw new NoSettingFoundException(provider);
		}
	}

}
