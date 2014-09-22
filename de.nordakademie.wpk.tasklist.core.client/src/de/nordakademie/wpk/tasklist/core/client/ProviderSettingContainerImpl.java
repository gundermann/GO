package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class ProviderSettingContainerImpl implements ProviderSettingContainer{

	@Override
	public ProviderSetting getSettings(Provider provider) throws NoSettingFoundException {
		SettingLoader loader = new SettingLoader();
			return loader.loadFromFile(provider);
	}

}
