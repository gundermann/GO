package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.server.service.google.GoogleProvider;

public class ProviderContainer {

	private static ProviderContainer _instance;
	GoogleProvider googleProvider;
	
	private ProviderContainer() {
		googleProvider = new GoogleProvider();
	}
	
	public static ProviderContainer getInstance(){
		if(_instance == null){
			_instance = new ProviderContainer();
		}
		return _instance;
	}

	public List<TaskList> delegateLoadAll(ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			return googleProvider.loadAll(setting);
		}
	}

}
