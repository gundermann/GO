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
		System.out.println(provider.toString());
		String directoryString = "C:\\tmp\\Provider\\" +provider.toString() +".json";
		
		ObjectMapper mapper = new ObjectMapper();
		ProviderSetting providersetting = null;
		try {
			File file = new File(directoryString);
			if(file.exists() && !file.isDirectory()){
				providersetting = (ProviderSetting) mapper.readValue(file, ProviderSettingImpl.class);
			}
			else{
				System.out.println("ProviderEinstellungen nicht gefunden!");
				throw new NoSettingFoundException(provider);
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return providersetting;
	}

}
