package de.nordakademie.wpk.tasklist.core.client;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;

public class SettingLoader {

	/**
	 * Lädt die Einstellungen für einen Provider aus einer Datei, welche nach dem Provider benannt ist.
	 * @param provider
	 * @return
	 * @throws NoSettingFoundException 
	 */
	public ProviderSetting loadFromFile(Provider provider) throws NoSettingFoundException {
		String directoryString = System.getProperty("User.home") +provider.toString() +".json";
		
		ObjectMapper mapper = new ObjectMapper();
		ProviderSetting providersetting = null;
		try {
			File file = new File(directoryString);
			if(file.exists() && !file.isDirectory()){
				providersetting = (ProviderSetting) mapper.readValue(file, ProviderSettingImpl.class);
			}
			else{
				throw new NoSettingFoundException(provider);
			}
			
		} catch (JsonParseException e) {
			System.out.println("JasonPaseExeprtion");
			throw new NoSettingFoundException(provider);
		} catch (JsonMappingException e) {
			System.out.println("JasonMappingExeprtion");
			System.out.println(e);
			throw new NoSettingFoundException(provider);
		} catch (IOException e) {
			System.out.println("JIOeExeprtion");
			throw new NoSettingFoundException(provider);
		}
		
		return providersetting;
	}

}
