package de.nordakademie.wpk.tasklist.core.client;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class ProviderSettingContainer {

	private static ProviderSettingContainer _instance;

	public static ProviderSettingContainer getInstance() {
		if (_instance == null)
			_instance = new ProviderSettingContainer();

		return _instance;
	}

	public ProviderSetting getProviderSetting(Provider provider)
			throws NoSettingFoundException {
		if (provider == Provider.GOOGLE)
			return new GoogleSetting();
		SettingLoader loader = new SettingLoader();
		return loader.loadFromFile(provider);
	}

	public List<ProviderSetting> getAllActiveProviderSettings() {
		List<ProviderSetting> providerSettings = new ArrayList<ProviderSetting>();
		Provider[] provider = Provider.values();
		for(int i = 0; i < provider.length;i++){
			try{
			ProviderSetting providerSetting = getProviderSetting(provider[i]);
			if(providerSetting.isActive()){
				providerSettings.add(providerSetting);
			}
			}catch(NoSettingFoundException e){
				continue;
			}
			
		}
		return providerSettings;
	}

}
