package de.nordakademie.wpk.tasklist.core.api;

public interface ProviderSettingContainer {

	ProviderSetting getSettings(Provider provider) throws NoSettingFoundException;
}
