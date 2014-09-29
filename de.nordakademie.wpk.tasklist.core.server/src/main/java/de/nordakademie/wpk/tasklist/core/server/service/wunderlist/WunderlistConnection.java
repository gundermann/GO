package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.AuthException;
import in.co.madhur.wunderjava.api.NetworkException;
import in.co.madhur.wunderjava.api.WunderList;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;

public class WunderlistConnection {
	
	private WunderList wunderlist;

	public WunderlistConnection(ProviderSetting setting) throws ServiceException {
		try {
			wunderlist = WunderList.getInstance(setting.getUsername(), setting.getPassword());
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
