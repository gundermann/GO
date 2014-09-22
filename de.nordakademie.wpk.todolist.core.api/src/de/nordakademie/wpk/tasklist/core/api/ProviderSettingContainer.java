package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;
import java.util.Set;

public class ProviderSettingContainer implements Serializable{

	private static final long serialVersionUID = 1L;
	private static ProviderSettingContainer _instance;
	private Set<ProviderSetting> providerSettings;
	
	public static ProviderSettingContainer getInstance(){
		if(_instance == null){
			_instance = new ProviderSettingContainer();
		}
		return _instance;
	}

	public ProviderSetting getSettings(Provider provider) throws NoSettingFoundException {
		return new ProviderSetting() {
			
			@Override
			public String getUserName() {
				return "gundermann.niels.ng@googlemail.com";
			}
			
			@Override
			public Provider getProvider() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
//		for (ProviderSetting providerSetting : providerSettings) {
//			if(providerSetting.getProvider().equals(provider))
//				return providerSetting;
//		}
//		throw new NoSettingFoundException(provider);
	}
	
	public void addPoviderSetting(ProviderSetting provider){
		providerSettings.add(provider);
	}
}
