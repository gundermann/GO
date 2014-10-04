package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.AuthException;
import in.co.madhur.wunderjava.api.NetworkException;
import in.co.madhur.wunderjava.api.WunderList;

/**
 * Verbindungsklasse für den Wunderlistprovider
 * 
 * @author Niels Gundermann
 *
 */
public class WunderlistConnection {
	

	public static WunderList getService(String username, String password) throws AuthException, NetworkException {
		return WunderList.getInstance(username, password);
	}

}
