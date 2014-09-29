package de.nordakademie.wpk.tasklist.core.client;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public interface ProviderSettingContainer {

	ProviderSetting getSettings(Provider provider)
			throws NoSettingFoundException;

}
