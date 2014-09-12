package de.nordakademie.wpk.todolist.core.api;

public interface ProviderSettingContainer {

	ProviderSetting getSettings(Provider provider) throws NoSettingFoundException;
}
