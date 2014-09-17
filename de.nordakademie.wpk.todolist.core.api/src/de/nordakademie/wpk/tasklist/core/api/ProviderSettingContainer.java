package de.nordakademie.wpk.tasklist.core.api;

import java.util.Set;

public class ProviderSettingContainer {

	private static ProviderSettingContainer _instance;
	private Set<ProviderSetting> providerSettings;
	
	public static ProviderSettingContainer getInstance(){
		if(_instance == null){
			_instance = new ProviderSettingContainer();
		}
		return _instance;
	}

	public ProviderSetting getSettings(Provider provider) throws NoSettingFoundException {
		for (ProviderSetting providerSetting : providerSettings) {
			if(providerSetting.getProvider().equals(provider))
				return providerSetting;
		}
		throw new NoSettingFoundException(provider);
	}
	
	public void addPoviderSetting(ProviderSetting provider){
		providerSettings.add(provider);
	}
}
