package de.nordakademie.wpk.tasklist.core.api;

public interface ProviderSetting {

	Provider getProvider();
	
	String getUsername();
	
	String getPassword();
	
	boolean isActive();
}
